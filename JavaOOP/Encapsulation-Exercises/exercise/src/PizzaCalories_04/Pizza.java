package PizzaCalories_04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {

    private String name;
    private Dough dough; //error ???
    private List<Topping> toppings;
    private int numberOfToppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setNumberOfToppings(numberOfToppings);

        this.toppings = new ArrayList<>();
    }

    private void setName(String name) {

        if (name.trim().isEmpty() || name.length() < 1 || name.length() > 15) {
            throw new IllegalArgumentException( name + " should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public void setNumberOfToppings(int numberOfToppings) {

        if (numberOfToppings > 10 || numberOfToppings < 0) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.numberOfToppings = numberOfToppings;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    private void setToppings(int number) {

        if (numberOfToppings > 10 || numberOfToppings < 0) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }

        this.toppings = toppings;
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping) {

        if (numberOfToppings > toppings.size()) {
            toppings.add(topping);
        }
    }

    public double getOverallCalories () {

        double doughCalories = this.dough.calculateCalories();

        double toppingsCalories = 0.0;

        for (Topping topping : toppings) {
            toppingsCalories += topping.calculateCalories();
        }

        double result = doughCalories + toppingsCalories;
        return  result;
    }
}
