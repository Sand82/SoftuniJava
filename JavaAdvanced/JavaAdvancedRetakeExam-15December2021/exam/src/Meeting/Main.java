package Meeting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(males::push); // queue

        ArrayDeque<Integer> females = Arrays.stream(scanner.nextLine()
                        .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new)); // stack

        int matchesCount = 0;

        while (!males.isEmpty() && !females.isEmpty()) {

            if (males.peek() <= 0 || females.peek() <= 0) {

                if (females.peek() <= 0) {
                    females.pop();
                } else {
                    males.poll();
                }
                continue;
            }

            if (males.peek() % 25 == 0 || females.peek() % 25 == 0) {

                if (males.peek() % 25 == 0) {
                    males.pop();
                    males.pop();
                } else {
                    females.poll();
                    females.poll();
                }
                continue;
            }

            int male = males.poll();
            int female = females.pop();

            if (male == female) {
                matchesCount++;
            } else if (male != female) { // where to add male ???
                male -= 2;
                males.push(male);
            }
        }

        System.out.printf("Matches: %d%n", matchesCount);

        String malesLeft = males.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.printf("Males left: %s", males.size() == 0 ? "none" : malesLeft);
        System.out.println();

        String femalesLeft = females.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.printf("Females left: %s", females.size() == 0 ? "none" : femalesLeft);
        System.out.println();
    }
}
