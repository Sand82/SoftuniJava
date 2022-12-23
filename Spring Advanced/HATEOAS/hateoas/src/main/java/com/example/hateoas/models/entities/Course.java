package com.example.hateoas.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private boolean enable;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Course setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public Course setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }
}
