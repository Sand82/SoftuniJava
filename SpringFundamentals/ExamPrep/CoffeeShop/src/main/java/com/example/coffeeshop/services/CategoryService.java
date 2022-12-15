package com.example.coffeeshop.services;

import com.example.coffeeshop.models.entities.Category;
import com.example.coffeeshop.models.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category getByCategoryNameEnum(CategoryNameEnum category);
}
