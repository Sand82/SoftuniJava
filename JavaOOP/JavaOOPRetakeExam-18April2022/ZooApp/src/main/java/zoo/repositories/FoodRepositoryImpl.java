package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class FoodRepositoryImpl implements FoodRepository {

    private Collection<Food> foods;

    public FoodRepositoryImpl() {

        this.foods = new ArrayList<>();
    }

    @Override
    public void add(Food food) {

        foods.add(food);
    }

    @Override
    public boolean remove(Food food) {

        return foods.remove(food);
    }

    @Override
    public Food findByType(String type) {

        return this.foods.stream()
                .filter(f -> f.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
