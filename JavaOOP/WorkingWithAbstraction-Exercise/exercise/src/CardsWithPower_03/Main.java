package CardsWithPower_03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CardRanks cardRank = CardRanks.valueOf(scanner.nextLine());
        FourSuits cardSuit = FourSuits.valueOf(scanner.nextLine());

        Card card = new Card(cardRank, cardSuit);

        System.out.printf("Card name: %s of %s; Card power: %d",
                cardRank.name(), cardSuit.name(), card.powerOfCard());
    }
}
