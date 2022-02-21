package com.solveus.domain.repository;

import com.solveus.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByUserID(Long userID);
    Optional<Auth> findById(Long authID);
}
