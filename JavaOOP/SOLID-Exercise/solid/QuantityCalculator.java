package solid;

import solid.products.Product;

import java.util.Collections;
import java.util.List;

public class QuantityCalculator implements MyCalculator {

    private List<Product> products;

    public QuantityCalculator(List<Product> products) {
        this.products = products;
    }

    @Override
    public double sum() {
        return products.stream().map(Product::getQuantity).reduce(0.0, Double::sum);
    }

    @Override
    public double average() {
        return sum() / products.size();
    }
}
