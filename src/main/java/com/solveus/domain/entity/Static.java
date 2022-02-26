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

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = true)
    private String view_1;

    @Column(nullable = true)
    private String view_2;

    @Column(nullable = true)
    private String view_3;

    @Column(nullable = true)
    private String view_4;

    @Column(nullable = true)
    private String view_5;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Integer answer;

    @Column(nullable = true)
    private Integer like_count;

    @Column(nullable = true)
    private Integer store_count;

    @Column(nullable = true)
    private Integer solved_count;

    @Column(nullable = true)
    private Integer comment_count;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator_id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Static(Long id, String title, Integer type, String view_1, String view_2, String view_3, String view_4, String view_5,Integer answer, Integer point, Integer like_count, Integer store_count, Integer solved_count, Integer comment_count, User creator_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.view_1 = view_1;
        this.view_2 = view_2;
        this.view_3 = view_3;
        this.view_4 = view_4;
        this.view_5 = view_5;
        this.point = point;
        this.answer = answer;
        this.like_count = like_count;
        this.store_count = store_count;
        this.solved_count = solved_count;
        this.comment_count = comment_count;
        this.creator_id = creator_id;
        this.created = created;
        this.updated = updated;
    }

}
