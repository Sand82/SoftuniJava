package com.example.mobilelele.model.entities;

import com.example.mobilelele.model.enums.EngineEnum;
import com.example.mobilelele.model.enums.TransmissionEnum;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private int year;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;


    public OfferEntity() {
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferEntity setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferEntity setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferEntity setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public ModelEntity getModel() {
        return model;
    }

    public OfferEntity setModel(ModelEntity model) {
        this.model = model;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }
}
