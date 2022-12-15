package com.example.coffeeshop.models.bindings;

import com.example.coffeeshop.models.entities.Category;
import com.example.coffeeshop.models.entities.User;
import com.example.coffeeshop.models.enums.CategoryNameEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderAddBindingModel {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

    @Size(min = 5)
    private String description;
    @NotNull
    private CategoryNameEnum category;

    private User user;

    public OrderAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public OrderAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddBindingModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public OrderAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderAddBindingModel setUser(User user) {
        this.user = user;
        return this;
    }
}
