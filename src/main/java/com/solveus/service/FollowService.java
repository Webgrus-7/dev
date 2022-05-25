package com.solveus.service;


import com.solveus.domain.dto.FollowDto;
import com.solveus.domain.dto.RelationDto;
import com.solveus.domain.dto.UserDto;
import com.solveus.domain.entity.FollowRelation;
import com.solveus.domain.entity.User;
import com.solveus.domain.repository.FollowRepository;
import com.solveus.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public FollowDto makeFollowDto(FollowRelation relation) {
        FollowDto followDto = FollowDto.builder()
                .id(relation.getId())
                .follower(relation.getFollower().getUserID())
                .following(relation.getFollowing().getUserID())
                .build();

        return followDto;
    }

    @Transactional
    public FollowDto newFollowing(User follower, String following_id) {
        // folloewr - 하는 사람, following - 당하는 사람

//        User follower = userRepository.findById(follower_id)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자(팔로워)입니다."));
        User following = userRepository.findByUserID(following_id)
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
        return makeFollowDto(newRelation);
    }

    @Transactional
    // 내가 팔로우한 것 취소
    public FollowDto removeFollowing(User follower, String following_id) {
        // follower - 취소 하는 사람(나), following - 취소 당하는 사람


        // 팔로잉 사용자의 존재 여부 확인
        User following = userRepository.findByUserID(following_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자(팔로잉)입니다."));


        // 1. 팔로워 -> 팔로잉 팔로우 관계인지 확인
        Optional<FollowRelation> alreadyFollowing = followRepository.findByFollowerAndFollowing(follower,following);
        if(alreadyFollowing.isPresent()) {
            // 팔로워 - 팔로잉 수 - 1
            follower.setFollowing_count(follower.getFollowing_count() - 1);
            userRepository.save(follower);
            // 팔로잉 - 팔로워 수 - 1
            following.setFollower_count(following.getFollower_count() - 1);
            userRepository.save(following);
            // 팔로우 - 팔로잉 관계 제거
            followRepository.delete(alreadyFollowing.get());
            return makeFollowDto(alreadyFollowing.get());
        }
        else{
            throw new IllegalArgumentException(following_id + "를 팔로우하고 있지 않습니다.");
        }

    }

    @Transactional
    // 나를 팔로우하는 사람 취소
    public FollowDto removeFollower(User following, String follower_id) {
        // following - 취소 하는 사람(나), follower - 취소 당하는 사람


        // 팔로워 사용자의 존재 여부 확인
        User follower = userRepository.findByUserID(follower_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자(팔로워)입니다."));


        // 1. 팔로워 -> 팔로잉 팔로우 관계인지 확인
        Optional<FollowRelation> alreadyFollower = followRepository.findByFollowerAndFollowing(follower,following);
        if(alreadyFollower.isPresent()) {
            // 팔로워 - 팔로잉 수 - 1
            follower.setFollowing_count(follower.getFollowing_count() - 1);
            userRepository.save(follower);
            // 팔로잉 - 팔로워 수 - 1
            following.setFollower_count(following.getFollower_count() - 1);
            userRepository.save(following);
            // 팔로우 - 팔로잉 관계 제거
            followRepository.delete(alreadyFollower.get());
            return makeFollowDto(alreadyFollower.get());
        }
        else{
            throw new IllegalArgumentException(follower_id + "가 사용자를 팔로우하고 있지 않습니다.");
        }

    }


    @Transactional
    public List<UserDto> getAllFollowingUser(User follower) {
        List<FollowRelation> allFollowing = followRepository.findAllByFollower(follower);
        List<UserDto> allFollowingUser = new ArrayList<>();

        for(FollowRelation r : allFollowing){
            allFollowingUser.add(userService.makeUserDto(r.getFollowing()));
        }
        return allFollowingUser;

    }

    @Transactional
    public List<UserDto> getAllFollowerUser(User following) {
        List<FollowRelation> allFollower = followRepository.findAllByFollowing(following);
        List<UserDto> allFollowerUser = new ArrayList<>();

        for(FollowRelation r : allFollower){
            allFollowerUser.add(userService.makeUserDto(r.getFollower()));
        }
        return allFollowerUser;

    }

    @Transactional
    public RelationDto isF4F(User user1, String user2_id) {
        // user2 존재 여부 확인
        User user2 = userRepository.findByUserID(user2_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));


        // 1. user1 -> user2 팔로우 관계인지 확인
        Optional<FollowRelation> oneToTwo = followRepository.findByFollowerAndFollowing(user1,user2);
        // 2. user2 -> user1 팔로우 관계인지 확인
        Optional<FollowRelation> TwoToOne = followRepository.findByFollowerAndFollowing(user2,user1);
        if(oneToTwo.isPresent() && TwoToOne.isPresent()){
            // 맞팔로우 관계
            return new RelationDto(true,true,true);
        }
        else if(oneToTwo.isPresent()) {
            // user1(나) -> user2 팔로우 관계
            return new RelationDto(true, false,false);
        } else if(TwoToOne.isPresent()){
            // user2 -> user1(나) 팔로우 관계
            return new RelationDto(false, true,false);
        } else{
            // 아무 관계 아닐때
            return new RelationDto(false,false,false);
        }
    }


}
