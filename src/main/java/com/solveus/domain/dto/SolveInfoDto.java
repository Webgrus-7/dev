package com.solveus.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SolveInfoDto {
    Long ProblemID;
    String UserID;
    Integer grade;
    LocalDateTime updated;

    @Builder
    public SolveInfoDto(Long problemID, String userID, Integer grade, LocalDateTime updated) {
        ProblemID = problemID;
        UserID = userID;
        this.grade = grade;
        this.updated = updated;
    }
}
