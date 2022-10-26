package solid.products;

public class Coke extends Drink {

    private static final double CALORIES_PER_100_GRAMS = 44.0;
    private static final double DENSITY = 0.6;

    public Coke(double milliliters) {
        super(milliliters);
    }

    @Override
    public double getAmountOfCalories() {
        return getMilliliters() * DENSITY * CALORIES_PER_100_GRAMS / 100;
    }
}
