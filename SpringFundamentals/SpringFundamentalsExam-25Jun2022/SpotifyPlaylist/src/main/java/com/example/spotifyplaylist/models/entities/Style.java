package com.example.spotifyplaylist.models.entities;

import com.example.spotifyplaylist.models.entities.enums.StyleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "style")
public class Style extends BaseEntity{

    @Column(nullable = false, unique = true)
    private StyleEnum name;

    @Column(columnDefinition = "TEXT")
    private String Description;

    public Style() {
    }

    public StyleEnum getName() {
        return name;
    }

    public Style setName(StyleEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public Style setDescription(String description) {
        Description = description;
        return this;
    }
}
