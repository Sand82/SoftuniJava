package PizzaCalories_04;

public enum ToppingsModifiers {

    MEAT(1.2),
    VEGGIES(0.8),
    CHEESE(1.1),
    SAUCE(0.9);

    private double calories;

    ToppingsModifiers(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
