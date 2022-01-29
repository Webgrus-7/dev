package com.solveus.domain.repository;

import com.solveus.domain.entity.Static;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticRepository extends JpaRepository<Static,Long> {
}
