package com.solveus.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    private String content;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private String field;

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

    @Column(nullable = true, columnDefinition = "Integer default 0")
    private Integer like_count = 0;

    @Column(nullable = true, columnDefinition = "Integer default 0")
    private Integer store_count = 0;

    @Column(nullable = true, columnDefinition = "Integer default 0")
    private Integer solved_count = 0;

    @Column(nullable = true, columnDefinition = "Integer default 0")
    private Integer comment_count = 0;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator_id;

    @OneToMany(mappedBy = "problemID")
    private List<LikeList> like_list = new ArrayList<LikeList>();

    @OneToMany(mappedBy = "problemID")
    private List<SolvedList> solvedList = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Static(Long id, String title,String content,String field, Integer type, String view_1, String view_2, String view_3, String view_4, String view_5,Integer answer, Integer point, Integer like_count, Integer store_count, Integer solved_count, Integer comment_count, User creator_id, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.field = field;
        this.type = type;
        this.view_1 = view_1;
        this.view_2 = view_2;
        this.view_3 = view_3;
        this.view_4 = view_4;
        this.view_5 = view_5;
        this.point = point;
        this.answer = answer;
        this.like_count = 0;
        this.store_count = 0;
        this.solved_count = 0;
        this.comment_count = 0;
        this.creator_id = creator_id;
        this.created = created;
        this.updated = updated;
    }

}
