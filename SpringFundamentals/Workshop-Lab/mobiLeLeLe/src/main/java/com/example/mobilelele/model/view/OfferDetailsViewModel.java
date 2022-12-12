package com.example.mobilelele.model.view;

import com.example.mobilelele.model.entities.enums.EngineEnum;
import com.example.mobilelele.model.entities.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class OfferDetailsViewModel {

    private Long id;

    private EngineEnum engine;

    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionEnum transmission;
    private String description;
    private int year;
    private String modelName;
    private String modelBrandName;
    private Instant created;
    private Instant modified;
    private String sellerFirstName;
    private String sellerLastName;
    public OfferDetailsViewModel() {
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferDetailsViewModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferDetailsViewModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailsViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferDetailsViewModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferDetailsViewModel setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public OfferDetailsViewModel setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public OfferDetailsViewModel setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
        return this;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsViewModel setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsViewModel setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferDetailsViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferDetailsViewModel setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
