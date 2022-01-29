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
@Table(name="Hashtags",schema = "solvewithus")
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private Long problem_id;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Hashtag(Long id, String tag, Long problem_id) {
        this.id = id;
        this.tag = tag;
        this.problem_id = problem_id;
    }
}
