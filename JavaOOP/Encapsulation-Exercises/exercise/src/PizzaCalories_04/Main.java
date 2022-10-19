package PizzaCalories_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> commands = new ArrayList<>();

        String pizzaInput = scanner.nextLine();

        while (!pizzaInput.equals("END")) {

            commands.add(pizzaInput);
            pizzaInput = scanner.nextLine();
        }

        String[] pizzaTokens = commands.get(0).split(" ");
        String pizzaName = pizzaTokens[1];
        int pizzaNumberOfToppings = Integer.parseInt(pizzaTokens[2]);

        String[] doughTokens = commands.get(1).split(" ");
        String doughFlourType = doughTokens[1];
        String doughBackingTechnique = doughTokens[2];
        int doughWeight = Integer.parseInt(doughTokens[3]);

        try {
            Pizza pizza = new Pizza(pizzaName, pizzaNumberOfToppings);
            Dough dough = new Dough(doughFlourType, doughBackingTechnique, doughWeight);

            pizza.setDough(dough);

            for (int i = 2; i < pizzaNumberOfToppings + 2; i++) {
                String[] toppingsTokens = commands.get(i).split(" ");
                String toppingsType = toppingsTokens[1];
                int toppingsWeight =Integer.parseInt(toppingsTokens[2]);

                Topping topping = new Topping(toppingsType, toppingsWeight);

                pizza.addTopping(topping);
            }

            double overallCalories = pizza.getOverallCalories();

            System.out.printf("%s - %.2f", pizza.getName(), overallCalories);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

    }
}
