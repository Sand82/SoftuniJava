package com.example.coffeeshop.repositories;

import com.example.coffeeshop.models.entities.Category;
import com.example.coffeeshop.models.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryNameEnum categoryNameEnum);
}
