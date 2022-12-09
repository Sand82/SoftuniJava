package com.example.mobilelele.model.view;

import com.example.mobilelele.model.enums.EngineEnum;
import com.example.mobilelele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class OfferSummeryViewModel {

    private Long id;
    private EngineEnum engine;

    private String imageUrl;

    private Integer mileage;

    private BigDecimal price;

    private TransmissionEnum transmission;

    private int year;

    private String modelName;

    private String modelBrandName;

    public OfferSummeryViewModel() {
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferSummeryViewModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummeryViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferSummeryViewModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferSummeryViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferSummeryViewModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferSummeryViewModel setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OfferSummeryViewModel setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public OfferSummeryViewModel setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferSummeryViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
