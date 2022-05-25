package com.solveus.service;


import com.solveus.domain.dto.ProblemDto;
import com.solveus.domain.dto.SolveDto;
import com.solveus.domain.dto.SolveInfoDto;
import com.solveus.domain.entity.SolvedList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.LikeListRepository;
import com.solveus.domain.repository.SolvedListRepository;
import com.solveus.domain.repository.StaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SolveService {

    private final StaticRepository staticRepository;
    private final SolvedListRepository solvedListRepository;
    private final ProblemService problemService;


    public SolveInfoDto makesolveInfoDto(SolvedList solvedList){
        SolveInfoDto result = SolveInfoDto.builder()
                .grade(solvedList.getGrade())
                .problemID(solvedList.getProblemID().getId())
                .userID(solvedList.getUserID().getUserID())
                .updated(solvedList.getUpdated())
                .build();

        return result;

    }
    @Transactional
    public SolveDto storeSolved(User user, SolveDto solve) {
        Static problem = staticRepository.findById(solve.getProblemID())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));

        // 이미 푼 문제이더라도 record로 저장 -> 점수가 다를 수 있기 때문
        SolvedList solved = SolvedList.builder()
                .grade(solve.getGrade())
                .user_id(user)
                .problem_id(problem)
                .build();

        // 문제 - 총 푼 횟수 + 1
        problem.setSolved_count(problem.getSolved_count() + 1);

        solvedListRepository.save(solved);
        staticRepository.save(problem);

        return solve;

    }

    @Transactional
    public List<SolveInfoDto> getUserAndProblemSolved(User user, Long problemId){

        Static problem = staticRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));

        List<SolvedList> solvedList = solvedListRepository.findAllByUserIDAndProblemID(user,problem);

        List<SolveInfoDto> result = new ArrayList<>();
        for(SolvedList sol: solvedList) {
            result.add(makesolveInfoDto(sol));
        }

        return result;


    }

    @Transactional
    public Set<ProblemDto> getAllSolvedProblem(User user) {
        List<Long> solvedList = solvedListRepository.getDistinctProblemIDByUserID(user);

        List<ProblemDto> result = new ArrayList<>();
        for(Long sol: solvedList) {

            result.add(problemService.makeProblemDto(staticRepository.findById(sol).get()));
        }

        Set<ProblemDto> no_dup = new HashSet<ProblemDto>(result);
        return no_dup;
    }

}
