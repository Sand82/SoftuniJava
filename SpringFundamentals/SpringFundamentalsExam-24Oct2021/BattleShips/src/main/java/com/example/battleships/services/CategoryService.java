package com.example.battleships.services;

import com.example.battleships.models.entities.Category;
import com.example.battleships.models.entities.enums.CategoryEnum;

public interface CategoryService {
    void createCategory();
    Category getCategoryByCategoryEnum(CategoryEnum category);
}
