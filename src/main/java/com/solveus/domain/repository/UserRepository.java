package com.solveus.domain.repository;

import com.solveus.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByUserID(String userID);
   Optional<User> findById(Long id);

   User findByPhone(String phone);

}
