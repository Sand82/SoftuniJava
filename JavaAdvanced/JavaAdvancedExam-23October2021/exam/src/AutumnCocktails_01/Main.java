package AutumnCocktails_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> buckets = Arrays.stream(scanner.nextLine().split("\\s+"))// queue
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> freshnessValue = new ArrayDeque<>();// stack
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).forEach(freshnessValue::push);

        Map<String, Integer> cocktails = new TreeMap<>();

        while (!buckets.isEmpty() && !freshnessValue.isEmpty()) {

            if (buckets.peek() == 0) {
                buckets.pop();
                continue;
            }

            int bucket = buckets.poll();
            int lastFreshValue = freshnessValue.pop();

            int mix = bucket * lastFreshValue;

            String cocktail = null;

            switch (mix) {

                case 150:

                    cocktail = "Pear Sour";
                    break;
                case 250:

                    cocktail = "The Harvest";
                    break;
                case 300:

                    cocktail = "Apple Hinny";
                    break;
                case 400:

                    cocktail = "High Fashion";
                    break;
            }
            if (cocktail == null) {

                bucket += 5;
                buckets.offerLast(bucket);
            } else {

                cocktails.putIfAbsent(cocktail, 0);
                cocktails.put(cocktail, cocktails.get(cocktail) + 1);
            }

        }

        if (cocktails.size() < 4) {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        } else {
            System.out.println("It's party time! The cocktails are ready!");
        }

        if (!buckets.isEmpty()) {
            int bucket = buckets.stream().reduce(0, (a,b) -> a + b);

            System.out.printf("Ingredients left: %d%n", bucket);
        }

        for (var drinks: cocktails.entrySet()) {
            System.out.printf(" # %s --> %d%n", drinks.getKey(), drinks.getValue());
        }
    }
}
