package com.example.battleships.services.impl;

import com.example.battleships.models.entities.Category;
import com.example.battleships.models.entities.enums.CategoryEnum;
import com.example.battleships.repositories.CategoryRepository;
import com.example.battleships.services.CategoryService;
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
    public void createCategory() {

        if (categoryRepository.count() == 0) {

            List<Category> categories = Arrays.stream(CategoryEnum.values()).map(c -> {
                Category category = new Category();

                category.setName(c);
                return category;

            }).toList();

            categoryRepository.saveAll(categories);
        }
    }

    @Override
    public Category getCategoryByCategoryEnum(CategoryEnum category) {

        return categoryRepository.findByName(category);
    }
}
