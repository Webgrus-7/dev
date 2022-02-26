package com.solveus.service;

import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.entity.Auth;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.AuthRepository;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.UserIDDuplicateException;
import com.solveus.security.JwtProvider;
import io.jsonwebtoken.Claims;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final SaltUtil saltUtil;
    private final JwtProvider jwtProvider;

    @Transactional
    public User save(User value) {
        Optional<User> alreadyUser = userRepository.findByUserID(value.getUserID());
        if(alreadyUser.isPresent())  throw new UserIDDuplicateException("UserID duplicated", ErrorCode.USERID_DUPLICATION);
        else {
            String password = value.getPassword();
            String salt = saltUtil.genSalt();
            value.setSalt(salt);
            value.setPassword(saltUtil.encodePassword(salt, password));
            value.setRoles(Collections.singletonList("ROLE_USER"));
        }

        return userRepository.save(value);
    }


    @Transactional
    public TokenResponse doLogin(LoginDto userRequest) throws Exception {
        User user = userRepository.findByUserID(userRequest.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if(!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getUserID());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        Optional<Auth> aluser = authRepository.findByUserID(user.getId());


        if(!aluser.isPresent()) {
            // 최초 로그인 -> 새로 발급
            Auth auth = new Auth();
            auth.setUserID(user.getId());
            auth.setRefreshToken(refreshToken);
            authRepository.save(auth);
        }else {
            // 해당 ID의 refreshToken update
            aluser.ifPresent(selectUser -> {
                selectUser.setRefreshToken(refreshToken);
                authRepository.save(selectUser);
            });
        }

        System.out.println(accessToken);
        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;

    }

    // accessToken issue
    public TokenResponse issueAccessToken(HttpServletRequest request) {
        String accessToken = jwtProvider.resolveAccessToken(request);
        String refreshToken = jwtProvider.resolveRefreshToken(request);
        System.out.println("accessToken = " + accessToken);
        System.out.println("refreshToken = " + refreshToken);


        // accessToken 만료여부 상관없이 요청 & RefreshToken 유효시 바로 재발급
//        if (!jwtProvider.isValidAccessToken(accessToken)) {
//            System.out.println("AccessToken 토큰 만료됨");
//            //accessToken: 만료, refreshToken: 유효
            if (jwtProvider.isValidRefreshToken(refreshToken)) // refresh 유효
            {
                System.out.println("Refresh는 유효함");
                Claims claims = jwtProvider.getClaimsFromRefreshToken(refreshToken);
                Long userID = Long.valueOf(claims.get("userID").toString());
                Optional<User> user = userRepository.findById(userID);
                String tokenFromDB = authRepository.findByUserID(user.get().getId()).get().getRefreshToken();
                if (refreshToken.equals(tokenFromDB)) {
                    // DB에서 찾은 refresh와 들어온 것이 같은지 확인
                    accessToken = jwtProvider.createAccessToken(user.get().getUserID());
                    System.out.println("Access Token 재발급 완료");
                } else {
                    System.out.println("RefreshTokenTampered");
                    //예외발생
                    refreshToken = null;
                    accessToken = null;
                }
            } else {
                // 입력으로 들어온 RefreshToken 유효 X
                refreshToken = null;
                accessToken = null;
            }
        //}
        TokenResponse token = new TokenResponse();
        token.setACCESS_TOKEN(accessToken);
        token.setREFRESH_TOKEN(refreshToken);
        return token;
    }

    // refreshToken null 일 경우 -> 재 로그인 필요
    // refreshToken 유효할 경우 재로그인 필요 없이 issueAccessToken 호출 통해 재발급

    @Transactional
    public User find_user(HttpServletRequest request) throws Exception {
        String accessToken = jwtProvider.resolveAccessToken(request);
        if(jwtProvider.isValidAccessToken(accessToken)){ //accessToken이 유효한 경우
            Claims accessClaims = jwtProvider.getClaimsFromAccessToken(accessToken);
            String userID = (String) accessClaims.get("userID");

            User user = userRepository.findByUserID(userID)
                    .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

            return user;
        }else throw  new Exception("토큰이 만료되었습니다.");
    }
}
