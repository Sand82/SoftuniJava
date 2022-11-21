package app.repositories;

import app.model.ingredients.BasicIngredient;
import app.model.ingredients.Ingredient;
import app.model.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BasicIngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<Ingredient> findByNameStartingWith(String charInput);

    Ingredient findByName(String name);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> names);

    @Modifying
    @Query("UPDATE BasicIngredient i SET i.price = i.price + i.price * :multiplier")
    void increasePriceByPercent(@Param("multiplier") BigDecimal actualPercent);
}
