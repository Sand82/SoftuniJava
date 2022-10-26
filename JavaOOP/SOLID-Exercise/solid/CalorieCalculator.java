package solid;

import solid.products.Product;
import java.util.List;

public class CalorieCalculator implements MyCalculator {

    private List<Product> products;

    public CalorieCalculator(List<Product> products) {
        this.products = products;
    }

    public double sum() {
        return  products.stream().map(Product::getAmountOfCalories).reduce(0.0, Double::sum);
    }

    public double average() {
        return sum() / products.size();
    }
}
