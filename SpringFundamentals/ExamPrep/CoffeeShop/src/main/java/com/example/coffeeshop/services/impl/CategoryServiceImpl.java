package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.models.entities.Category;
import com.example.coffeeshop.models.enums.CategoryNameEnum;
import com.example.coffeeshop.repositories.CategoryRepository;
import com.example.coffeeshop.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {

        if (categoryRepository.count() == 0) {

            List<Category> categories = Arrays.stream(CategoryNameEnum.values()).map(c -> {
                Category category = new Category();
                category.setName(c);

                switch (category.getName()) {
                    case CAKE -> category.setNeededTime(10);
                    case OTHER -> category.setNeededTime(5);
                    case COFFEE -> category.setNeededTime(2);
                    case DRINK -> category.setNeededTime(1);
                }

                return category;
            }).toList();

            categoryRepository.saveAll(categories);
        }
    }

    @Override
    public Category getByCategoryNameEnum(CategoryNameEnum category) {

        return categoryRepository.findByName(category);
    }
}
