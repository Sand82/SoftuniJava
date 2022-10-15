import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandsOfCards {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, LinkedHashSet<String>> playerCards = new LinkedHashMap<>();

        Map<String, Integer> result = new LinkedHashMap<>();

        while (true) {

            String[] input = scanner.nextLine().split(": ");

            String playerName = input[0];

            if (playerName.equals("JOKER")) {
                break;
            }

            String[] cartsInput = input[1].split(", ");

            if (!playerCards.containsKey(playerName)) {
                playerCards.put(playerName, new LinkedHashSet<String>());
            }

            var currentCart = playerCards.get(playerName);
            Collections.addAll(currentCart, cartsInput);
            playerCards.put(playerName, currentCart);
        }

        for (var player : playerCards.entrySet()) {
            String [] cartSet = addCartInCllection(player.getValue());

            int currentResult = playerScore(cartSet);

            result.put(player.getKey(), currentResult);
        }

        for (var item : result.entrySet()) {
            System.out.println(String.format("%s: %d", item.getKey(), item.getValue()));
        }
    }

    private static String[] addCartInCllection(Set<String> carts) {

        String[] result = new String[carts.size()];
        int counter = 0;
        for (var cart : carts) {
            result[counter] = cart;
            counter++;
        }

        return result;
    }

    private static int playerScore(String[] carts) {

        int result = 0;
        for (int i = 0; i < carts.length; i++) {
            result += getCardValue(carts[i].substring(0, carts[i].length() - 1)) * getColorStraight(carts[i].substring(carts[i].length() - 1));
        }

        return result;
    }

    private static int getColorStraight(String cardColor) { // (S -> 4, H-> 3, D -> 2, C -> 1).
        int result = 0;
        switch (cardColor) {
            case "S":
                result = 4;
                break;
            case "H":
                result = 3;
                break;
            case "D":
                result = 2;
                break;
            case "C":
                result = 1;
                break;
        }

        return result;
    }

    private static int getCardValue(String value) {

        switch (value) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(value);
        }
    }
}
