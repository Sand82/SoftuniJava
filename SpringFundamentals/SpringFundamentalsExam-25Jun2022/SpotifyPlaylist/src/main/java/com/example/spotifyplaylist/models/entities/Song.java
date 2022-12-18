package com.example.spotifyplaylist.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    @Column(nullable = false)
    private String performer;
    @Column(nullable = false)
    private String title;
    private Integer Duration;
    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    public Song() {
    }

    public String getPerformer() {
        return performer;
    }

    public Song setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return Duration;
    }

    public Song setDuration(Integer duration) {
        Duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Song setStyle(Style style) {
        this.style = style;
        return this;
    }
}
