package com.example.battleships.models.bindings;

import com.example.battleships.models.entities.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddShipBindingModel {

    @Size(min = 2, max = 10)
    private String name;
    @Positive
    @NotNull
    private Integer power;
    @Positive
    @NotNull
    private Integer health;
    @PastOrPresent
    @NotNull
    private LocalDate created;
    @NotNull
    private CategoryEnum category;

    public AddShipBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddShipBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public AddShipBindingModel setPower(Integer power) {
        this.power = power;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public AddShipBindingModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public AddShipBindingModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddShipBindingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
