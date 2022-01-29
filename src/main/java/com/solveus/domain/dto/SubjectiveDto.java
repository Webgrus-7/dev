package com.solveus.domain.dto;

import com.solveus.domain.entity.Subjective;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SubjectiveDto {

    private Long id;
    private Long problem_id;
    private String question;
    private String answer;
    private Integer p_type;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Subjective toEntity(){
        Subjective build = Subjective.builder()
                .id(id)
                .problem_id(problem_id)
                .question(question)
                .answer(answer)
                .p_type(p_type)
                .build();
        return build;
    }

    @Builder
    public SubjectiveDto(Long id, Long problem_id, String question, String answer, Integer p_type, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.problem_id = problem_id;
        this.question = question;
        this.answer = answer;
        this.p_type = p_type;
        this.created = created;
        this.updated = updated;
    }
}
