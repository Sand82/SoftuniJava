package solid.products;

public abstract class Drink implements Product {

    private double milliliters;

    public Drink(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
