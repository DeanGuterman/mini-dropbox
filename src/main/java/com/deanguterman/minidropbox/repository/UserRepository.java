package com.deanguterman.minidropbox.repository;

import com.deanguterman.minidropbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository for the User class
public interface UserRepository extends JpaRepository<User, Long>{
    // JPA automatically implements these functions
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);

}
