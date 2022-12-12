package com.example.pathfinder.model.view;

public class RouteViewModel {

    private Long id;
    private String description;
    private String name;
    private String pictureUrl;

    public RouteViewModel() {
    }

    public Long getId() {
        return id;
    }

    public RouteViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
