package com.solveus.controller;

import com.solveus.domain.dto.NewProblem;
import com.solveus.domain.dto.ProblemDto;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.service.AuthService;
import com.solveus.service.ProblemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

    private final AuthService authService;
    private final ProblemService problemService;

    //문제 생성
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<ProblemDto> create(@RequestBody NewProblem value, HttpServletRequest request) throws Exception {

        User creator_id = authService.find_user(request);

        Static problem = Static.builder()
                .creator_id(creator_id)
                .title(value.getTitle())
                .field(value.getField())
                .content(value.getContent())
                .type(value.getType())
                .view_1(value.getView_1())
                .view_2(value.getView_2())
                .view_3(value.getView_3())
                .view_4(value.getView_4())
                .view_5(value.getView_5())
                .answer(value.getAnswer())
                .point(value.getPoint())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(problemService.save(problem));
    }

    // 사용자가 생성한 문제 목록
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<List<ProblemDto>> userProblem(HttpServletRequest request) throws Exception {
        User creator = authService.find_user(request);
        List<Static> statics = creator.getProblems();
        List<ProblemDto> result = new ArrayList<>();
        for(Static s: statics){
            ProblemDto show = problemService.makeProblemDto(s);
            result.add(show);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    //전체 문제 목록
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<ProblemDto>> AllProblem() throws Exception {

        return ResponseEntity.status(HttpStatus.OK)
                .body(problemService.getAllProblems());
    }

    @RequestMapping(value = "/like/{problemID}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class ),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<Integer> likeCount(HttpServletRequest request ,@PathVariable("problemID") Long problemID) throws  Exception {
        User userID = authService.find_user(request);
        Integer like_count = problemService.addLikeCount(userID,problemID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(like_count);
    }




}
