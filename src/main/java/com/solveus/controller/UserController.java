package com.solveus.controller;

import com.solveus.domain.dto.JoinDto;
import com.solveus.domain.dto.LoginDto;
import com.solveus.domain.dto.TokenResponse;
import com.solveus.domain.entity.User;
import com.solveus.domain.dto.UserDto;
import com.solveus.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public List<UserDto> list(HttpServletRequest request) {
        List<UserDto> userDtoList = userService.getAllUser();

        return userDtoList;
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public UserDto userInfo(HttpServletRequest request ,@PathVariable("userID") String userID) {
        //userService.getUserByPhone();
        return userService.getUserByUserID(userID);
    }



    // 접근 테스트 -> 인터셉터로 막혀있음
    @PostMapping("/test")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public Map userResponseTest(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }


}
