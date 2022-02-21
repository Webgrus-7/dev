package com.solveus;

import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.entity.Auth;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.AuthRepository;
import com.solveus.domain.repository.UserRepository;
import com.solveus.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class LoginTest {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  AuthRepository authRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtProvider jwtProvider;

    @Test
    public void doLogin() throws Exception {
        LoginDto userRequest = new LoginDto("user1", "solve1234");

        User user = userRepository.findByUserID(userRequest.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getUserID());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        Optional<Auth> aluser = authRepository.findByUserID(user.getId());

        System.out.println(aluser);
        if (!aluser.isPresent()) {
            // 최초 로그인 -> 새로 발급
            Auth auth = new Auth();
            auth.setUserID(user.getId());
            auth.setRefreshToken(refreshToken);
            authRepository.save(auth);
            System.out.println("저장되나?");
        } else {
            // 해당 ID의 refreshToken update;
            aluser.ifPresent(selectUser -> {
                selectUser.setRefreshToken(refreshToken);
                authRepository.save(selectUser);
            });
        }

        System.out.println(accessToken);
    }

}
