package com.solveus.service;

import com.solveus.domain.dto.ProblemDto;
import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.LikeList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.LikeListRepository;
import com.solveus.domain.repository.StaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final StaticRepository staticRepository;
    private final LikeListRepository likeListRepository;
   // private final FollowService followService;


    public ProblemDto makeProblemDto(Static value) {
        ProblemDto result = ProblemDto.builder()
                .id(value.getId())
                .creator_id(value.getCreator_id().getId())
                .creator_nick(value.getCreator_id().getNickname())
                .creator_userID(value.getCreator_id().getUserID())
                .title(value.getTitle())
                .content(value.getContent())
                .field(value.getField())
                .type(value.getType())
                .view_1(value.getView_1())
                .view_2(value.getView_2())
                .view_3(value.getView_3())
                .view_4(value.getView_4())
                .view_5(value.getView_5())
                .point(value.getPoint())
                .answer(value.getAnswer())
                .like_count(value.getLike_count())
                .solve_count(value.getSolved_count())
                .updated(value.getUpdated())
                .build();
        return result;
    }

    @Transactional
    public ProblemDto save(Static problem) {
        Static value =  staticRepository.save(problem);

        ProblemDto result = makeProblemDto(value);

        return result;
    }

    @Transactional
    public List<ProblemDto> getAllProblems() {
        List<Static> statics = staticRepository.findAll();

        List<ProblemDto> result = new ArrayList<>();
        for(Static s: statics){
            ProblemDto show = makeProblemDto(s);
            result.add(show);
        }
        return result;
    }

    @Transactional
    public Integer addLikeCount(User user, Long problemID) {
        Static problem = staticRepository.findById(problemID)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));

        Optional<LikeList> alreadyLike = likeListRepository.findByUserIDAndProblemID(user,problem);
        if(alreadyLike.isPresent()) {
            // 이미 하트를 누른 관계 -> 하트 삭제
            likeListRepository.deleteById(alreadyLike.get().getId());
            problem.setLike_count(problem.getLike_count() - 1);
            staticRepository.save(problem);
        }else {
            // 이제 하트를 누름 -> 하트 관계 추가
            LikeList new_relation = LikeList.builder()
                    .user_id(user)
                    .problem_id(problem)
                    .build();
            likeListRepository.save(new_relation);
            problem.setLike_count(problem.getLike_count() + 1);
            staticRepository.save(problem);
        }
        return problem.getLike_count();

    }

    @Transactional
    public List<ProblemDto> getAllFollowingProblem(List<UserDto> allFollowingUsers) {

        List<Static> problems = new ArrayList<>();
        // 팔로잉한 사용자가 만든 모든 문제 목록
        for(UserDto following : allFollowingUsers) {
            problems.addAll(staticRepository.findAllByCreator_id(following.getId()));
        }

        List<ProblemDto> problemDtos = new ArrayList<>();
        for(Static stat : problems) {
            problemDtos.add(makeProblemDto(stat));
        }

        return problemDtos;
    }


}
