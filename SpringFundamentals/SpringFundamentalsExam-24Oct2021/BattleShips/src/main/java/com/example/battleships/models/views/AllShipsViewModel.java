package com.example.battleships.models.views;

import jakarta.persistence.Column;

public class AllShipsViewModel {

    private String name;
    private Integer health;
    private Integer power;
    public AllShipsViewModel() {
    }

    public String getName() {
        return name;
    }

    public AllShipsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public AllShipsViewModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public AllShipsViewModel setPower(Integer power) {
        this.power = power;
        return this;
    }
}
