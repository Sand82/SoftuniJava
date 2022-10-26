package solid.products;

public class Chips extends Food {

    private static final double CALORIES_PER_100_GRAMS = 529.0;

    public Chips(double grams) {
        super(grams);
    }

    @Override
    public double getAmountOfCalories() {
        return getGrams() * CALORIES_PER_100_GRAMS / 100;
    }
}
