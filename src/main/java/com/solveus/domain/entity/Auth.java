package com.solveus.domain.entity;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="auth",schema = "solvewithus")
public class Auth {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userID;

    @Column(nullable = false)
    private String refreshToken;

}
