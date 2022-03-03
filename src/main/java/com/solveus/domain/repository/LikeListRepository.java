package com.solveus.domain.repository;


import com.solveus.domain.entity.Interest;
import com.solveus.domain.entity.LikeList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeListRepository extends JpaRepository<LikeList,Long> {
    Optional<LikeList> findByUserIDAndProblemID(User user_id, Static problem_id);

}
