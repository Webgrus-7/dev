package com.solveus.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationDto {
    boolean following;
    boolean follower;
    boolean F4F; // 맞팔로우
}
