package com.solveus.domain.dto;

import com.solveus.domain.entity.Static;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StaticDto {

    private Long id;
    private Long creator_id;
    private Integer like_count;
    private Integer store_count;
    private Integer solved_count;
    private Integer comment_count;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Static toEntity(){
        Static build = Static.builder()
                .id(id)
                .creator_id(creator_id)
                .like_count(like_count)
                .store_count(store_count)
                .solved_count(solved_count)
                .comment_count(comment_count)
                .build();
        return build;
    }

    @Builder
    public StaticDto(Long id, Long creator_id, Integer like_count, Integer store_count, Integer solved_count, Integer comment_count, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.creator_id = creator_id;
        this.like_count = like_count;
        this.store_count = store_count;
        this.solved_count = solved_count;
        this.comment_count = comment_count;
        this.created = created;
        this.updated = updated;
    }
}
