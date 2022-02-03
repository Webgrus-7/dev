package com.solveus.domain.dto;

import com.solveus.domain.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JoinDto {


    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message= "올바른 이메일 주소를 입력해주세요")
    private String email;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userID;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    private String major;
    private String intro;

    public User toEntity() {
        User build = User.builder()
                .userID(userID)
                .email(email)
                .phone(phone)
                .password(password)
                .nickname(nickname)
                .major(major)
                .intro(intro)
                .build();
        return build;
    }

    @Builder
    public JoinDto(String userID, String email, String phone,String password, String nickname, String major, String intro) {
        this.userID = userID;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
        this.major = major;
        this.intro = intro;
    }
}
