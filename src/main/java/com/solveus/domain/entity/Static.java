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
@Table(name="Problem_statics",schema = "solvewithus")
public class Static {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private Integer like_count;

    @Column(nullable = true)
    private Integer store_count;

    @Column(nullable = true)
    private Integer solved_count;

    @Column(nullable = true)
    private Integer comment_count;

    @Column(nullable = true)
    private Long creator_id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Static(Long id, String title, Integer like_count, Integer store_count, Integer solved_count, Integer comment_count, Long creator_id) {
        this.id = id;
        this.title = title;
        this.like_count = like_count;
        this.store_count = store_count;
        this.solved_count = solved_count;
        this.comment_count = comment_count;
        this.creator_id = creator_id;
    }
}
