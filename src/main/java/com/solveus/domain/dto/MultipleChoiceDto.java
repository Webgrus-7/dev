package com.solveus.domain.dto;

import com.solveus.domain.entity.MultipleChoice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MultipleChoiceDto {

    private Long id;
    private Long problem_id;
    private String question;
    private String choices;
    private Integer answer;
    private Integer p_type;
    private LocalDateTime created;
    private LocalDateTime updated;

    public MultipleChoice toEntity(){
        MultipleChoice build = MultipleChoice.builder()
                .id(id)
                .problem_id(problem_id)
                .question(question)
                .choices(choices)
                .answer(answer)
                .p_type(p_type)
                .build();
        return build;
    }

    @Builder
    public MultipleChoiceDto(Long id, Long problem_id, String question, String choices, Integer answer, Integer p_type, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.problem_id = problem_id;
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.p_type = p_type;
        this.created = created;
        this.updated = updated;
    }
}
