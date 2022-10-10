package Bouquets_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tulips = new ArrayDeque<>(); // stack
        Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).forEach(tulips::push);

        ArrayDeque<Integer> daffodils = Arrays.stream(scanner.nextLine().split(", ")) // queue
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int bouquets = 0;

        List<Integer> flowersStore = new ArrayList<>();

        while (!tulips.isEmpty() && !daffodils.isEmpty()) {

            int daffodil = daffodils.poll();
            int tulip = tulips.pop();

            int sum = daffodil + tulip;

            while (sum > 15) {

                tulip -= 2;
                sum = tulip + daffodil;
            }

            if (sum == 15) {
                bouquets++;
            } else if (sum < 15) {
                flowersStore.add(sum);
            }
        }

        System.out.println();

        bouquets += flowersStore.stream().reduce(0, (a, b) -> a + b) / 15;

        if (bouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %s bouquets!%n", bouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.%n", 5 - bouquets);
        }
    }
}
