package com.example.mobilelele.model.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Column
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<ModelEntity> models;

    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}


