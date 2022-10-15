package PastryShop_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split(" "))// queue
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredients = new ArrayDeque<>(); // stack

        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).forEach(ingredients::push);

        Map<String, Integer> products = new LinkedHashMap<>();
        products.put("Biscuit", 0);
        products.put("Cake", 0);
        products.put("Pie", 0);
        products.put("Pastry", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int liquid = liquids.poll();
            int ingredient = ingredients.pop();

            int mix = liquid + ingredient;

            String result = null;

            switch (mix) {
                case 25:
                    result = "Biscuit";
                    break;
                case 50:
                    result = "Cake";
                    break;
                case 75:
                    result = "Pie";
                    break;
                case 100:
                    result = "Pastry";
                    break;
            }

            if (result != null) {
                products.put(result, products.get(result) + 1);
            } else {
                ingredient += 3;
                ingredients.offerFirst(ingredient);
            }
        }

        int countCookedProducts = (int) products.entrySet().stream().filter(p -> p.getValue() == 0).count();

        if (countCookedProducts == 0) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        List<String> liquidsList = new ArrayList<>();

        for (var item : liquids) {
            liquidsList.add(String.valueOf(item));
        }

        String liquidsLeft = liquidsList.size() == 0 ? "none" : String.join(", ", liquidsList);

        System.out.printf("Liquids left: %s%n", liquidsLeft);

        List<String> ingredientList = new ArrayList<>();

        for (var item : ingredients) {
            ingredientList.add(String.valueOf(item));
        }

        String ingredientsLeft = ingredientList.size() == 0 ? "none" : String.join(", ", ingredientList);

        System.out.printf("Ingredients left: %s%n", ingredientsLeft);

        for (var product : products.entrySet()) {
            System.out.printf("%s: %d%n", product.getKey(), product.getValue());
        }
    }
}
