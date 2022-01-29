package com.solveus.domain.repository;

import com.solveus.domain.entity.Subjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectiveRepository extends JpaRepository<Subjective,Long> {
}
