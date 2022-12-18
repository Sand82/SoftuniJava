package com.example.spotifyplaylist.models.views;

import com.example.spotifyplaylist.models.entities.Style;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class AllSongViewModel {

    private Long id;
    private String performer;
    private String title;
    private Integer Duration;
    private Style style;

    public AllSongViewModel() {
    }

    public Long getId() {
        return id;
    }

    public AllSongViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AllSongViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return Duration;
    }

    public AllSongViewModel setDuration(Integer duration) {
        Duration = duration;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public AllSongViewModel setStyle(Style style) {
        this.style = style;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public AllSongViewModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }
}
