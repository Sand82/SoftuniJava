package PizzaCalories_04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topping {
    private String toppingType;
    private double weight;

    private List<String> toppings = new ArrayList<>(List.of("Meat", "Veggies", "Cheese", "Sauce"));

    public Topping(String toppingType, double weight) {

        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) { // meat, veggies, cheese, or sauce

        if (!toppings.contains(toppingType)){

            throw new IllegalArgumentException("Cannot place "+ toppingType +" on top of your pizza.");
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {

        if (weight <= 0 || weight > 50) {
            throw new IllegalArgumentException("{Topping type name} weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    public double calculateCalories () {

        ToppingsModifiers toppingsModifiersEnum = ToppingsModifiers.valueOf(this.toppingType.toUpperCase());

        double result = toppingsModifiersEnum.getCalories() * this.weight * 2;

        return result;
    }
}
