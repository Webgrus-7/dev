package com.solveus.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
@Setter
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

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Static problemID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userID;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public SolvedList(Long id, Integer grade, Integer p_count, Static problem_id, User user_id) {
        this.id = id;
        this.grade = grade;
        this.p_count = 0;
        this.problemID = problem_id;
        this.userID = user_id;
    }
}
