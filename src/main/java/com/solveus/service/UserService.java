package com.solveus.service;

import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.UserRepository;
import com.solveus.exception.ErrorCode;
import com.solveus.exception.UserIDDuplicateException;
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
                    .userID(user.getUserID())
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
    public UserDto getUserByUserID(String userID) {
        User user = userRepository.findByUserID(userID);

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .userID(user.getUserID())
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
        User alreadyUser = userRepository.findByUserID(value.getUserID());
        if(alreadyUser!= null){
            throw new UserIDDuplicateException("UserID duplicated", ErrorCode.USERID_DUPLICATION);
        }
        String password = value.getPassword();
        String salt= saltUtil.genSalt();
        value.setSalt(salt);
        value.setPassword(saltUtil.encodePassword(salt, password));

        return userRepository.save(value);
    }

}
