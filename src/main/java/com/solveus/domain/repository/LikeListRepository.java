package com.solveus.domain.repository;


import com.solveus.domain.entity.Interest;
import com.solveus.domain.entity.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeListRepository extends JpaRepository<Interest, LikeList> {
}
