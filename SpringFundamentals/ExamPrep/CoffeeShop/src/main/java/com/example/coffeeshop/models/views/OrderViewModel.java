package com.example.coffeeshop.models.views;

import com.example.coffeeshop.models.entities.Category;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class OrderViewModel {

    private Long id;
    private String name;
    private BigInteger price;
    private Category category;

    public OrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public OrderViewModel setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public OrderViewModel setCategory(Category category) {
        this.category = category;
        return this;
    }
}
