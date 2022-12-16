package com.example.likebook.repositories;

import com.example.likebook.models.entities.Mood;
import com.example.likebook.models.entities.enums.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    Mood findByName(MoodEnum moodEnum);
}
