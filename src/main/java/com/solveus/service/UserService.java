package com.solveus.service;

import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.PhoneDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltUtil saltUtil;

    @Transactional
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(User user: userList){
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .major(user.getMajor())
                    .intro(user.getIntro())
                    .nickname(user.getNickname())
                    .build();
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Transactional
    public UserDto getUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone);

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .major(user.getMajor())
                .intro(user.getIntro())
                .nickname(user.getNickname())
                .build();

        return userDto;
    }

    @Transactional
    public User save(User value) {
        User alreadyUser = userRepository.findByPhone(value.getPhone());
        if(alreadyUser!= null){
            throw new PhoneDuplicateException("phone duplicated", ErrorCode.PHONE_DUPLICATION);
        }
        String password = value.getPassword();
        String salt= saltUtil.genSalt();
        value.setSalt(salt);
        value.setPassword(saltUtil.encodePassword(salt, password));

        return userRepository.save(value);
    }

}
