package com.solveus.domain.repository;

import com.solveus.domain.entity.SolvedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedListRepository extends JpaRepository<SolvedList,Long> {
}
