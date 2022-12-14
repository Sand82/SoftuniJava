package com.example.spotifyplaylist.repositories;

import com.example.spotifyplaylist.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
    User findByUsernameAndPassword(String username, String password);
}
