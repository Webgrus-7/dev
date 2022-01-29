package com.solveus.domain.dto;

import com.solveus.domain.entity.Hashtag;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HashtagDto {
    private Long id;
    private String tag;
    private Long problem_id;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Hashtag toEntity(){
        Hashtag build = Hashtag.builder()
                .id(id)
                .tag(tag)
                .problem_id(problem_id)
                .build();
        return build;
    }

    @Builder
    public HashtagDto(Long id, String tag, Long problem_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.tag = tag;
        this.problem_id = problem_id;
        this.created = created;
        this.updated = updated;
    }
}
