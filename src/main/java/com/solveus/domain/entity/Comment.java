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
@Table(name="Comments",schema = "solvewithus")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition="TEXT",nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long problem_id;

    @Column(nullable = false)
    private Long creator_id;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Comment(Long id, String contents, Long problem_id, Long creator_id) {
        this.id = id;
        this.contents = contents;
        this.problem_id = problem_id;
        this.creator_id = creator_id;
    }
}
