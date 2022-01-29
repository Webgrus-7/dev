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
@Table(name="User_solved_lists",schema = "solvewithus")
public class SolvedList {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private Integer grade;

    @Column(nullable = true)
    private Integer p_count;

    @Column(nullable = true)
    private Long problem_id;

    @Column(nullable = true)
    private Long user_id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public SolvedList(Long id, Integer grade, Integer p_count, Long problem_id, Long user_id) {
        this.id = id;
        this.grade = grade;
        this.p_count = p_count;
        this.problem_id = problem_id;
        this.user_id = user_id;
    }
}
