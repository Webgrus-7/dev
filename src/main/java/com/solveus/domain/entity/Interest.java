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
@Table(name="User_interests",schema = "solvewithus")
public class Interest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userID;

    @Column(nullable = true)
    private String interest1;

    @Column(nullable = true)
    private String interest2;

    @Column(nullable = true)
    private String interest3;

    @Column(nullable = true)
    private String interest4;

    @Column(nullable = true)
    private String interest5;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Builder
    public Interest(Long id, Long userID, String interest1, String interest2, String interest3, String interest4, String interest5) {
        this.id = id;
        this.userID = userID;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.interest4 = interest4;
        this.interest5 = interest5;
    }
}
