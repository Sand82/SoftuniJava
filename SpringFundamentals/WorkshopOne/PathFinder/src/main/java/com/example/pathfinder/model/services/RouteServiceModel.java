package com.example.pathfinder.model.services;

import com.example.pathfinder.model.entities.Category;
import com.example.pathfinder.model.entities.Picture;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.Set;

public class RouteServiceModel {

    private Long id;
    private String name;
    private String description;
    private String gpxCoordinates;
    private LevelEnum level;
    private String videoUrl;
    private User author;
    private Set<Category> categories;
    private Set<Picture> pictures;

    public RouteServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public RouteServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteServiceModel setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteServiceModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public RouteServiceModel setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public RouteServiceModel setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public RouteServiceModel setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }
}
