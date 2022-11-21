package app.repositories;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicIngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<Ingredient> findByNameStartingWith(String charInput);
}
