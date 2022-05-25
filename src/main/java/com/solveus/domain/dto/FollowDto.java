package com.solveus.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FollowDto  {
    private Long id;
    private String follower;
    private String following;

    @Builder
    public FollowDto(Long id, String follower, String following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }
}
