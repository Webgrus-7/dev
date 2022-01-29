package com.solveus.domain.dto;

import com.solveus.domain.entity.Interest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InterestDto {
    private Long id;
    private Long userID;
    private String interest1;
    private String interest2;
    private String interest3;
    private String interest4;
    private String interest5;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Interest toEntity(){
        Interest build = Interest.builder()
                .id(id)
                .userID(userID)
                .interest1(interest1)
                .interest2(interest2)
                .interest3(interest3)
                .interest4(interest4)
                .interest5(interest5)
                .build();
        return build;
    }

    @Builder
    public InterestDto(Long id, Long userID, String interest1, String interest2, String interest3, String interest4, String interest5, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.userID = userID;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.interest4 = interest4;
        this.interest5 = interest5;
        this.created = created;
        this.updated = updated;
    }
}
