package com.solveus.controller;

import com.solveus.domain.dto.ProblemDto;
import com.solveus.domain.dto.SolveDto;
import com.solveus.domain.dto.SolveInfoDto;
import com.solveus.domain.entity.User;
import com.solveus.service.AuthService;
import com.solveus.service.ProblemService;
import com.solveus.service.SolveService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class SolveController {


    private final AuthService authService;
    private final SolveService solveService;

    // 문제 풀기 완료 후 저장
    @RequestMapping(value = "/solved/{problemID}", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<SolveDto> storeSolved(HttpServletRequest request, @RequestBody SolveDto solve) throws Exception {
        User userID = authService.find_user(request);
        SolveDto result = solveService.storeSolved(userID, solve);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    // 문제에 대한 사용자의 풀기 기록 리스트
    @RequestMapping(value = "/solved/problem", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<List<SolveInfoDto>> storeProblemSolved(HttpServletRequest request, @RequestParam Long problemID) throws Exception {
        User userID = authService.find_user(request);

        List<SolveInfoDto> result = solveService.getUserAndProblemSolved(userID, problemID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);

    }

    // 사용자의 모든 문제 푼 list
    @RequestMapping(value = "/solved", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCESS_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "REFRESH_TOKEN", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    })
    public ResponseEntity<Set<ProblemDto>> storeProblemSolved(HttpServletRequest request) throws Exception {
        User userID = authService.find_user(request);

        Set<ProblemDto> result = solveService.getAllSolvedProblem(userID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
