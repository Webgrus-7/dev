package com.solveus;

import com.solveus.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("패스워드 암호화 테스트")
    void setPasswordEncoder() {
        String rawPassword = "1234";

        String encodedPassword = passwordEncoder.encode(rawPassword);


        System.out.println(System.getProperty("user.home"));
        assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword), // 평문이랑 암호화랑 다른지
                () -> assertTrue(passwordEncoder.matches(rawPassword,encodedPassword)) // 같은 패스워드 맞는지
        );
    }
}
