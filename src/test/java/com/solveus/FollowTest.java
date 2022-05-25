package com.solveus;

import com.solveus.domain.entity.FollowRelation;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.FollowRepository;
import com.solveus.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FollowTest {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void addFollowing() {

        User follower = userRepository.findByUserID("user1")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자(팔로워)입니다."));

        User following = userRepository.findByUserID("user6")
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자(팔로잉)입니다."));


        // 1. 이미 팔로잉 했는지 확인
        Optional<FollowRelation> alreadyFollowing = followRepository.findByFollowerAndFollowing(follower,following);
        if(alreadyFollowing.isPresent()) {
            throw new IllegalArgumentException("이미 팔로잉했습니다.");
        }
        // 팔로워 - 팔로잉 수 + 1
        follower.setFollowing_count(follower.getFollowing_count() + 1);
        userRepository.save(follower);
        // 팔로잉 - 팔로워 수 + 1
        following.setFollower_count(following.getFollower_count() + 1);
        userRepository.save(following);
        // 팔로우 - 팔로잉 관계 추가
        FollowRelation newRelation = FollowRelation.builder()
                .follower(follower)
                .following(following)
                .build();
        followRepository.save(newRelation);
        System.out.println(newRelation);
    }
}
