package PizzaCalories_04;

public enum DoughModifiers {

    WHITE(1.5),
    WHOLEGRAIN(1.0),
    CRISPY(0.9),
    CHEWY(1.1),
    HOMEMADE(1.0);

    private double calories;

    DoughModifiers(double calories) {
        this.calories = calories;

    }

    public double getCalories() {
        return calories;
    }
}