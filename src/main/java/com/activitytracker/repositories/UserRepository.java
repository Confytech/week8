package com.activitytracker.repositories;

import com.activitytracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String Password);
    Optional<User> findByEmailAndPassword(String email, String password);
}
