package com.solveus.domain.repository;

import com.solveus.domain.entity.FollowRelation;
import com.solveus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowRelation, Long> {
    Optional<FollowRelation> findByFollowerAndFollowing(User follower, User following);
    List<FollowRelation> findAllByFollower(User follower);
    List<FollowRelation> findAllByFollowing(User following);
}
