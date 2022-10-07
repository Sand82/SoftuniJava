package ItsChocolateTime_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Double> milks = Arrays.stream(scanner.nextLine() // -> queue
                        .split("\\s+")).map(Double::parseDouble)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Double> cacaoPowders = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine()
                        .split(" "))
                .map(Double::parseDouble)
                .forEach(cacaoPowders::push);

        Map<String, Integer> chocolates = new TreeMap<>();

        while (!milks.isEmpty() && !cacaoPowders.isEmpty()) {
            Double milk = milks.poll();
            Double cacaoPowder = cacaoPowders.pop();

            int percentage = (int) (cacaoPowder * 100 / (milk + cacaoPowder));

            String chocolateType = null;

            switch (percentage) {
                case 30:
                    chocolateType = "Milk Chocolate";
                    break;
                case 50:
                    chocolateType = "Dark Chocolate";
                    break;
                case 100:
                    chocolateType = "Baking Chocolate";
                    break;
            }

            if (chocolateType == null) {
                milk += 10;
                milks.offer(milk);
            }else{
                chocolates.putIfAbsent(chocolateType, 0);
                chocolates.put(chocolateType,chocolates.get(chocolateType) + 1);
            }
        }

        if (chocolates.size() < 3) {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }else {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        }

        for (var product: chocolates.entrySet()) {
            System.out.printf("# %s --> %d%n",product.getKey(), product.getValue());
        }
    }
}
