package Cooking_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine() // queue -> liquids
                        .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))// stack -> ingredients
                .map(Integer::parseInt)
                .forEach(ingredients::push);

        Map<String, Integer> cookedMils = new TreeMap<>();
        cookedMils.put("Bread", 0);
        cookedMils.put("Cake", 0);
        cookedMils.put("Pastry", 0);
        cookedMils.put("Fruit Pie", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {

            int liquid = liquids.poll();
            int ingredient = ingredients.pop();

            int sum = liquid + ingredient;

            String cook;

            switch (sum) {
                case 25:
                    cook = "Bread";
                    break;
                case 50:
                    cook = "Cake";
                    break;
                case 75:
                    cook = "Pastry";
                    break;
                case 100:
                    cook = "Fruit Pie";
                    break;
                default:
                    cook = null;
                    break;
            }

            if (cook == null) {
                int ingredientToPush = ingredient + 3;
                ingredients.push(ingredientToPush);

            } else {
                int newMileCount = cookedMils.get(cook) + 1;
                cookedMils.put(cook, newMileCount);
            }
        }

        boolean isSucceededCooking = cookedMils.entrySet().stream().anyMatch(x -> x.getValue() == 0);

        if (!isSucceededCooking) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }else{
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        System.out.println(String.
                format("Liquids left: %s",
                        liquids.isEmpty() == true ?
                                "none" :
                                liquids.stream().map(String::valueOf).collect(Collectors.joining(", "))));


        System.out.println(String
                .format("Ingredients left: %s",
                        ingredients.isEmpty() == true ?
                                "none" :
                                ingredients.stream().map(String::valueOf).collect(Collectors.joining(", "))));


        for (var product: cookedMils.entrySet()) {
            System.out.println(String.format("%s: %d", product.getKey(), product.getValue()));
        }
    }
}
