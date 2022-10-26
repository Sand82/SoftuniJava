package solid;

import solid.products.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>(List.of(
                new Coke(200),
                new Chips(250),
                new Chocolate(80),
                new Lemonade(250)
        ));

        CalorieCalculator calculator = new CalorieCalculator(products);
        ConsolePrinter consolePrinter = new ConsolePrinter(calculator);
        QuantityCalculator quantityCalculator = new QuantityCalculator(products);
        ConsolePrinter consolePrinter1 = new ConsolePrinter(quantityCalculator);

        consolePrinter.printSum();
        consolePrinter.printAverage();
        consolePrinter1.printSum();
        consolePrinter1.printAverage();
    }
}
