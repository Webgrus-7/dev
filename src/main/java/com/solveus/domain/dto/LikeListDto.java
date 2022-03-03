package com.solveus.domain.dto;

import com.solveus.domain.entity.LikeList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeListDto {
    private Long id;
    private User user_id;
    private Static problem_id;
    private LocalDateTime created;
    private LocalDateTime updated;

    public LikeList toEntity(){
        LikeList build = LikeList.builder()
                .id(id)
                .user_id(user_id)
                .problem_id(problem_id)
                .build();
        return build;
    }

    @Builder
    public LikeListDto(Long id, User user_id, Static problem_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.user_id = user_id;
        this.problem_id = problem_id;
        this.created = created;
        this.updated = updated;
    }
}
