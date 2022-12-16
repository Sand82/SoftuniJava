package com.example.likebook.repositories;

import com.example.likebook.models.entities.Post;
import com.example.likebook.models.entities.enums.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
