package ShoppingSpree_03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {

        this.setName(name);
        this.setMoney(money);

        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {

        if (product.getCost() <= money) {

            money -= product.getCost();
            products.add(product);

            System.out.println(this.getName() + " bought " + product.getName());
        } else {

            System.out.println(this.getName() + " can't afford " + product.getName());
        }
    }

    private void setName(String name) {

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    private void setMoney(double money) {

        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
}
