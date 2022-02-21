package com.solveus.service;

import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.Auth;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.AuthRepository;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.UserIDDuplicateException;
import com.solveus.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final SaltUtil saltUtil;
    private final JwtProvider jwtProvider;


    @Transactional
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(User user: userList){
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .userID(user.getUserID())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .major(user.getMajor())
                    .intro(user.getIntro())
                    .nickname(user.getNickname())
                    .build();
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Transactional
    public UserDto getUserByUserID(String userID) {
        User user = userRepository.findByUserID(userID)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .userID(user.getUserID())
                .phone(user.getPhone())
                .email(user.getEmail())
                .major(user.getMajor())
                .intro(user.getIntro())
                .nickname(user.getNickname())
                .build();

        return userDto;
    }


    // 비밀번호 확인
    @Transactional
    public boolean passwordCheck(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }


}
