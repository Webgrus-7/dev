package com.solveus;

import com.solveus.domain.entity.User;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.PhoneDuplicateException;
import com.solveus.service.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserTest {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SaltUtil saltUtil;

    @Test
    public void save() {
        User user = User.builder()
                .name("주주")
                .email("kk@gmail.com")
                .phone("010-1111-2222")
                .nickname("wnwn")
                .password("solve1234")
                .major("com")
                .intro("sid")
                .build();

        User alreadyUser = userRepository.findByPhone(user.getPhone());
        if(alreadyUser!= null){
            throw new PhoneDuplicateException("phone duplicated", ErrorCode.PHONE_DUPLICATION);
        }
        String password = user.getPassword();
        String salt= saltUtil.genSalt();
        user.setSalt(salt);
        user.setPassword(saltUtil.encodePassword(salt, password));


        userRepository.save(user);

    }
}
