package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {

        this.setName(name);
        this.capacity = capacity;

        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {

        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {

        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {

        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {

        foods.add(food);
    }

    @Override
    public void feed() {

        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {

        String animalsString = animals.isEmpty()
                ? "none"
                : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Animals: %s", animalsString));
        sb.append(System.lineSeparator());
        sb.append(String.format("Foods: %d", foods.size()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Calories: %d", sumCalories()));

        return sb.toString();
    }
}
