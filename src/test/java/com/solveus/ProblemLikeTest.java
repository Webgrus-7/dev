package com.solveus;

import com.solveus.domain.entity.LikeList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.AuthRepository;
import com.solveus.domain.repository.LikeListRepository;
import com.solveus.domain.repository.StaticRepository;
import com.solveus.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProblemLikeTest {

    @Autowired
    private  StaticRepository staticRepository;

    @Autowired
    private LikeListRepository likeListRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void addLikeCount(){

        User user = userRepository.findByPhone("010-1111-1112");
        Static problem = staticRepository.findById(94L)
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

        System.out.println(problem.getLike_count());
    }
}
