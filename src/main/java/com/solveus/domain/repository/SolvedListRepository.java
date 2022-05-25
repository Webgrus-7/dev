package com.solveus.domain.repository;

import com.solveus.domain.entity.SolvedList;
import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolvedListRepository extends JpaRepository<SolvedList,Long> {

   List<SolvedList> findAllByUserIDAndProblemID(User user_id, Static problem_id);

   @Query("SELECT DISTINCT problemID.id from SolvedList where userID = :user_id")
   List<Long> getDistinctProblemIDByUserID(@Param("user_id")User user_id);

}

