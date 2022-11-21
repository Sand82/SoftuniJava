package com.example.bookshop.services;

import com.example.bookshop.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
