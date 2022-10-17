package CardSuit_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fourSuits = scanner.nextLine();

        if (fourSuits.equals("Card Suits")) {
            System.out.println("Card Suits:");
            for (var cardSuits: FourSuits.values()) {
                System.out.printf("Ordinal value: %d; Name value: %s%n",
                        cardSuits.ordinal(), cardSuits.name());
            }
        }
    }
}
