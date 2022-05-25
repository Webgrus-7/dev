package com.solveus.service;

import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.ProblemDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.Auth;
import com.solveus.domain.entity.LikeList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.AuthRepository;
import com.solveus.domain.repository.StaticRepository;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.UserIDDuplicateException;
import com.solveus.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
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
    private final StaticRepository staticRepository;
    private final SaltUtil saltUtil;
    private final JwtProvider jwtProvider;

    @Autowired
    private ProblemService problemService;


    public UserDto makeUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .userID(user.getUserID())
                .phone(user.getPhone())
                .email(user.getEmail())
                .major(user.getMajor())
                .intro(user.getIntro())
                .nickname(user.getNickname())
                .follower_count(user.getFollower_count())
                .following_count(user.getFollowing_count())
                .build();

        return  userDto;
    }
    @Transactional
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(User user: userList){
            UserDto userDto = makeUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Transactional
    public UserDto getUserByUserID(String userID) {
        User user = userRepository.findByUserID(userID)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        UserDto userDto = makeUserDto(user);

        return userDto;
    }


    // 비밀번호 확인
    @Transactional
    public boolean passwordCheck(User user, String password){
        return passwordEncoder.matches(password, user.getPassword());
    }

    // 사용자가 좋아요 누른 문제 리스트
    @Transactional
    public List<ProblemDto> getAllLike(User user) {
        List<LikeList> likelists = user.getLike_list();
        List<ProblemDto> problemDtos = new ArrayList<>();
        // 관계 -> 문제 리스트 만들기
        for (LikeList like: likelists){
            Static problem = like.getProblemID();
            problemDtos.add(problemService.makeProblemDto(problem));
        }
        return problemDtos;
    }


}
