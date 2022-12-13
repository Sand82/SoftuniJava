package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.Category;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryNameEnum c) {
        return categoryRepository.findByName(c).get();
    }
}
