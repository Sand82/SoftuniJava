package com.example.pathfinder.model.bindings;

import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class RouteAddBindingModel {

    @Size(min = 3, max = 20, message = "Route name must be between 3 and 20 symbols.")
    private String name;
    @Size(min = 5)
    @NotNull
    private String description;
    private MultipartFile gpxCoordinates;
    @NotNull
    private LevelEnum level;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;

    public RouteAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public RouteAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddBindingModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteAddBindingModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public RouteAddBindingModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
