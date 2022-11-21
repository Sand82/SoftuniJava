package com.example.bookshop.entities;

import com.example.bookshop.repositories.CategoryRepository;
import com.example.bookshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {

        int size = (int) this.categoryRepository.count();

        Set<Integer> categoriesId = new HashSet<>();

        for (int i = 0; i < 5; i++) {

            int categoryId = new Random().nextInt(size + 1);

            categoriesId.add(categoryId);
        }

        List<Category> categoriesList =  this.categoryRepository.findAllById(categoriesId);

        return new HashSet<>(categoriesList);
    }
}
