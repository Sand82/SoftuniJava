package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column( nullable = false)
    @Lob
    private String gpxCoordinates;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @ManyToOne
    private User author;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Route() {
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Route setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public Route setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Route setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
