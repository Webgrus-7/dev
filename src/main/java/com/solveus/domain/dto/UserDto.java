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
    private String userID;
    private String email;
    private String phone;
    private String password;
    private String nickname;
    private String major;
    private String intro;
    private String salt;
    private Integer following_count;
    private Integer follower_count;
    private LocalDateTime created;
    private LocalDateTime updated;

    public User toEntity() {
        User build = User.builder()
                .id(id)
                .userID(userID)
                .phone(phone)
                .password(password)
                .nickname(nickname)
                .major(major)
                .intro(intro)
                .salt(salt)
                .follower_count(follower_count)
                .following_count(following_count)
                .build();
        return build;
    }

    @Builder
    public UserDto(Long id,String userID, String email, String phone, String nickname, String major, String intro,Integer following_count, Integer follower_count, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.userID = userID;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.major = major;
        this.intro = intro;
        this.follower_count = follower_count;
        this.following_count = following_count;
        this.created = created;
        this.updated = updated;
    }
}
