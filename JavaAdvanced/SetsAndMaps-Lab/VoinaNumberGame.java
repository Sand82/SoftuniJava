import java.util.*;
import java.util.stream.Collectors;

public class VoinaNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> firstPlayerSet = Arrays.stream(scanner.nextLine()
                .split(" ")).map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Integer> secondPlayerSet = Arrays.stream(scanner.nextLine()
                .split(" ")).map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        int roundCounter = 50;

        while (!firstPlayerSet.isEmpty() && ! secondPlayerSet.isEmpty()){

            if (roundCounter == 0) {
                break;
            }

            Iterator<Integer> firstPlayerIterator = firstPlayerSet.iterator();
            int firstPlayerNumber = firstPlayerIterator.next();
            firstPlayerIterator.remove();

            Iterator<Integer> secondPlayerIterator = secondPlayerSet.iterator();
            int secondPlayerNumber = secondPlayerIterator.next();
            secondPlayerIterator.remove();

            if (firstPlayerNumber > secondPlayerNumber) {
                firstPlayerSet.add(firstPlayerNumber);
                firstPlayerSet.add(secondPlayerNumber);
            }else if (firstPlayerNumber < secondPlayerNumber){
                secondPlayerSet.add(firstPlayerNumber);
                secondPlayerSet.add(secondPlayerNumber);
            }

            roundCounter--;
        }
        if (firstPlayerSet.size()> secondPlayerSet.size()) {
            System.out.println("First player win!");
        }else if (firstPlayerSet.size() < secondPlayerSet.size()) {
            System.out.println("Second player win!");
        }else {
            System.out.println("Draw");
        }
    }
}
