package com.example.spotifyplaylist.models.bindings;

import com.example.spotifyplaylist.models.entities.enums.StyleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class SongAddBindingModel {

    @Size(min = 3, max = 20)
    private String performer;

    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    private LocalDate releaseDate;

    @Positive
    private Integer Duration;

    @NotNull
    private StyleEnum style;

    public SongAddBindingModel() {
    }

    public String getPerformer() {
        return performer;
    }

    public SongAddBindingModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDuration() {
        return Duration;
    }

    public SongAddBindingModel setDuration(Integer duration) {
        Duration = duration;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public SongAddBindingModel setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
