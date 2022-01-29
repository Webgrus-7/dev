package com.solveus.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name="Problems_content_es",schema = "solvewithus")
public class Subjective {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long problem_id;

    @Column(columnDefinition =  "TEXT", nullable = false)
    private String question;

    @Column(columnDefinition =  "TEXT",nullable = false)
    private String answer;

    @Column(nullable = false)
    private Integer p_type;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Subjective(Long id, Long problem_id, String question, String answer, Integer p_type) {
        this.id = id;
        this.problem_id = problem_id;
        this.question = question;
        this.answer = answer;
        this.p_type = p_type;
    }
}
