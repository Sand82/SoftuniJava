package com.example.battleships.models.views;

public class ShipViewModel {

    private Long ownerId;

    private String name;

    public ShipViewModel() {
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ShipViewModel setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
