package com.example.battleships.repositories;

import com.example.battleships.models.entities.Category;
import com.example.battleships.models.entities.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryEnum category);
}
