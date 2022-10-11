package MagicBox_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstMagicBox = Arrays.stream(scanner.nextLine().split("\\s+")) //queue
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondMagicBox = new ArrayDeque<>(); //stack

        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).forEach(secondMagicBox::push);

        List<Integer> claimedItems = new ArrayList<>();

        while (!firstMagicBox.isEmpty() && !secondMagicBox.isEmpty()){

            int firstInt = firstMagicBox.peek();
            int secondInt = secondMagicBox.peek();

            int sum = firstInt + secondInt;
            if (sum % 2 == 0) {

                claimedItems.add(sum);

                firstMagicBox.poll();
                secondMagicBox.pop();
            }else {

              int lastItem = secondMagicBox.pop();
              firstMagicBox.offerLast(lastItem);
            }
        }

        if (firstMagicBox.isEmpty()) {

            System.out.println("First magic box is empty.");
        }else {

            System.out.println("Second magic box is empty.");
        }

        int sumClaimedItems = claimedItems.stream().reduce(0, (a, b) -> a + b);

        if (sumClaimedItems >= 90) {

            System.out.printf("Wow, your prey was epic! Value: %d%n", sumClaimedItems);
        }else {

            System.out.printf("Poor prey... Value: %d", sumClaimedItems);
        }
    }
}
