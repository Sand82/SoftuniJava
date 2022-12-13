package com.example.pathfinder.service;

import com.example.pathfinder.model.entities.Category;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {

   Category findCategoryByName(CategoryNameEnum c);
}
