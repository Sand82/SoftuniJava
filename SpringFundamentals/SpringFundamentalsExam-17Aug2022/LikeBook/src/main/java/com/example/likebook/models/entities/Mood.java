package com.example.likebook.models.entities;

import com.example.likebook.models.entities.enums.MoodEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity {

    @Column(nullable = false, unique = true)
    private MoodEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood() {
    }

    public MoodEnum getName() {
        return name;
    }

    public Mood setName(MoodEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
