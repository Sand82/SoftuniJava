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

    private Instant year;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;
}
