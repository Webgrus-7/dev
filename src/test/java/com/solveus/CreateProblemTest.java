package com.solveus;

import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.StaticRepository;
import com.solveus.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class CreateProblemTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaticRepository staticRepository;


    @Test
    public void newProblem(){

        User user = userRepository.findByPhone("010-1111-1112");
        Static problem = Static.builder()
                .creator_id(user)
                .title("이문제 답??? ")
                .content("내용ㅇ용")
                .field("분야야")
                .type(1)
                .view_1("답답답")
                .view_2(null)
                .view_3(null)
                .view_4(null)
                .view_5(null)
                .answer(1)
                .point(5)
                .build();

        System.out.println(staticRepository.save(problem).getLike_count());
    }

}
