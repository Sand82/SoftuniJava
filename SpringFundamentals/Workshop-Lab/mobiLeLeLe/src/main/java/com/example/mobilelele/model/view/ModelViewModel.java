package com.example.mobilelele.model.view;

import com.example.mobilelele.model.enums.CategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ModelViewModel {

    private String name;
    private CategoryEnum category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;

    public ModelViewModel() {
    }

    public String getName() {
        return name;
    }

    public ModelViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ModelViewModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelViewModel setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelViewModel setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    @Override
    public String toString() {
        return "ModelViewModel{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }
}
