package com.solveus.domain.dto;

import com.solveus.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String nickname;
    private String major;
    private String intro;
    private String salt;
    private LocalDateTime created;
    private LocalDateTime updated;

    public User toEntity() {
        User build = User.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .password(password)
                .nickname(nickname)
                .major(major)
                .intro(intro)
                .salt(salt)
                .build();
        return build;
    }

    @Builder
    public UserDto(Long id, String name, String email, String phone, String nickname, String major, String intro, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.major = major;
        this.intro = intro;
        this.created = created;
        this.updated = updated;
    }
}
