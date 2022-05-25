package com.solveus.controller;

import com.solveus.domain.dto.FollowDto;
import com.solveus.domain.dto.RelationDto;
import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.FollowRelation;
import com.solveus.domain.entity.User;
import com.solveus.service.AuthService;
import com.solveus.service.FollowService;
import com.solveus.service.ProblemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RelationService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/relation")
public class RelationController {

    private final AuthService authService;
    private final ProblemService problemService;
    private final FollowService followService;
    // 팔로우 하기
    @RequestMapping(value = "/following/{userID}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<FollowDto> following(HttpServletRequest request , @PathVariable("userID") String following) throws  Exception {
        User follower = authService.find_user(request);
        FollowDto relation = followService.newFollowing(follower,following);
        return ResponseEntity.status(HttpStatus.OK)
                .body(relation);
    }

    // 팔로우 취소하기(언팔로우) - 내가 팔로우한 것 삭제 [내가 팔로워에 해당]
    @RequestMapping(value = "/unfollowing/{userID}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<FollowDto> unfollowing(HttpServletRequest request , @PathVariable("userID") String following) throws  Exception {
        User follower = authService.find_user(request);
        FollowDto relation = followService.removeFollowing(follower,following);
        return ResponseEntity.status(HttpStatus.OK)
                .body(relation);
    }


    // 팔로워 삭제하기 - 나의 팔로워를 내가 삭제 [내가 팔로잉에 해당]
    @RequestMapping(value = "/unfollower/{userID}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<FollowDto> unfollower(HttpServletRequest request , @PathVariable("userID") String follower) throws  Exception {
        User following = authService.find_user(request);
        FollowDto relation = followService.removeFollower(following,follower);
        return ResponseEntity.status(HttpStatus.OK)
                .body(relation);
    }


    // 팔로잉 사용자 목록(내가 팔로워에 해당)
    @RequestMapping(value = "/following",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<List<UserDto>> getAllFollowing(HttpServletRequest request) throws Exception {
        User follower = authService.find_user(request);
        List<UserDto> allRelation = followService.getAllFollowingUser(follower);
        return ResponseEntity.status(HttpStatus.OK)
                .body(allRelation);
    }

    // 팔로워 사용자 목록(내가 팔로잉에 해당)
    @RequestMapping(value = "/follower",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<List<UserDto>> getAllFollower(HttpServletRequest request) throws Exception {
        User following = authService.find_user(request);
        List<UserDto> allRelation = followService.getAllFollowerUser(following);
        return ResponseEntity.status(HttpStatus.OK)
                .body(allRelation);
    }

    // 팔로우 확인 용도 api
    @RequestMapping(value = "/check/{userID}",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<RelationDto> isF4F(HttpServletRequest request, @PathVariable("userID") String user2) throws Exception {
        User user1 = authService.find_user(request);
        RelationDto checkRelation = followService.isF4F(user1,user2);
        return ResponseEntity.status(HttpStatus.OK)
                .body(checkRelation);
    }


}
