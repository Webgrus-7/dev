package com.solveus.domain.dto;

import com.solveus.domain.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String contents;
    private Long problem_id;
    private Long creator_id;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Comment toEntity(){
        Comment build = Comment.builder()
                .id(id)
                .contents(contents)
                .creator_id(creator_id)
                .problem_id(problem_id)
                .build();
        return build;
    }

    public CommentDto(Long id, String contents, Long problem_id, Long creator_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.contents = contents;
        this.problem_id = problem_id;
        this.creator_id = creator_id;
        this.created = created;
        this.updated = updated;
    }
}
