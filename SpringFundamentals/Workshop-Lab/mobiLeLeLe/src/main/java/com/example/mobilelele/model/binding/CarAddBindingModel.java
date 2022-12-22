package com.example.mobilelele.model.binding;

import com.example.mobilelele.model.entities.enums.EngineEnum;
import com.example.mobilelele.model.entities.enums.TransmissionEnum;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

public class CarAddBindingModel {

    @NotNull
    private String model;
    @Positive
    private BigDecimal Price;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;
    @Min(1920)
    @Max(2023)
    private int year;
    @Positive
    private Integer mileage;

    private String description;
    @URL
    private String imageUrl;

    public CarAddBindingModel() {
    }

    public String getModel() {
        return model;
    }

    public CarAddBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public CarAddBindingModel setPrice(BigDecimal price) {
        Price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public CarAddBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public CarAddBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public CarAddBindingModel setYear(int year) {
        this.year = year;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public CarAddBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CarAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CarAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
