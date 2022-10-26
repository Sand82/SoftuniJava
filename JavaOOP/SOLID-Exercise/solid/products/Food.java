package solid.products;

public abstract class Food implements Product {

    private double grams;

    public Food(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
