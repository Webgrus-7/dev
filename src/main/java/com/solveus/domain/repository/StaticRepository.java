package com.solveus.domain.repository;

import com.solveus.domain.entity.Static;
import com.solveus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaticRepository extends JpaRepository<Static,Long> {
    @Query("select s from Static s where s.creator_id.id =:userIdx")
    List<Static> findAllByCreator_id(@Param("userIdx") Long userIdx);
}
