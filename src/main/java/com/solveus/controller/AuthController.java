package com.solveus.controller;

import com.solveus.domain.dto.JoinDto;
import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.entity.User;
import com.solveus.service.AuthService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    // 회원가입
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseEntity<User> join(@RequestBody JoinDto value) throws Exception {

        User user = User.builder()
                .userID(value.getUserID())
                .email(value.getEmail())
                .phone(value.getPhone())
                .nickname(value.getNickname())
                .password(value.getPassword())
                .major(value.getMajor())
                .intro(value.getIntro())
                .following_count(value.getFollowing_count())
                .follower_count(value.getFollower_count())
                .build();



        User responseUser = authService.save(user);
        if(responseUser == null) {throw new Exception("회원 저장 실패");}
        else return ResponseEntity.status(HttpStatus.OK).body(responseUser);

    }

    //사용자 로그인 - accessToken return
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDto user) throws Exception {
//
        TokenResponse token = authService.doLogin(user);
        System.out.println(token.getACCESS_TOKEN());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    // Accesstoken 재발급 요청
    @PostMapping("/accessToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity issueAccessToken(HttpServletRequest request) throws Exception {
        return ResponseEntity
                .ok()
                .body(authService.issueAccessToken(request));
    }
}
