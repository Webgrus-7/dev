package com.solveus.controller;

import com.solveus.domain.dto.JoinDto;
import com.solveus.domain.entity.User;
import com.solveus.domain.dto.UserDto;
import com.solveus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

//    public UserController(UserService userService){
//        this.userService = userService;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserDto> list() {
        List<UserDto> userDtoList = userService.getAllUser();

        return userDtoList;
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    public UserDto userInfo(@PathVariable("userID") String userID) {
        //userService.getUserByPhone();
        return userService.getUserByUserID(userID);
    }


    @RequestMapping(value = "/local/new", method = RequestMethod.POST)
    public ResponseEntity<User> join(@RequestBody JoinDto value) throws Exception {

        User user = User.builder()
                .userID(value.getUserID())
                .email(value.getEmail())
                .phone(value.getPhone())
                .nickname(value.getNickname())
                .password(value.getPassword())
                .major(value.getMajor())
                .intro(value.getIntro())
                .build();



        User responseUser = userService.save(user);
        if(responseUser == null) {throw new Exception("회원 저장 실패");}
        else return ResponseEntity.status(HttpStatus.OK).body(responseUser);

    }
}
