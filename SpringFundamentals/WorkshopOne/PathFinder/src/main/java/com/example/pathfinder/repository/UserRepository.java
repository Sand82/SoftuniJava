package com.example.pathfinder.repository;

import com.example.pathfinder.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String userName, String password);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
}
