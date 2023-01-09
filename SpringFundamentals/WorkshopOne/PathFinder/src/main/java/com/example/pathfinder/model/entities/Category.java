package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryNameEnum name;

    @Lob
    private String description;

    public Category() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name == category.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
