package CardRank_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fourSuits = scanner.nextLine();

        if (fourSuits.equals("Card Ranks")) {
            System.out.println("Card Ranks:");
            for (var card: CardRanks.values()) {
                System.out.printf("Ordinal value: %d; Name value: %s%n",
                        card.ordinal(), card.name());
            }
        }
    }
}
