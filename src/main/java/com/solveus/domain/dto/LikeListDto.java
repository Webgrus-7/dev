package com.solveus.domain.dto;

import com.solveus.domain.entity.LikeList;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeListDto {
    private Long id;
    private Long user_id;
    private Long problem_id;
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
    public LikeListDto(Long id, Long user_id, Long problem_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.user_id = user_id;
        this.problem_id = problem_id;
        this.created = created;
        this.updated = updated;
    }
}
