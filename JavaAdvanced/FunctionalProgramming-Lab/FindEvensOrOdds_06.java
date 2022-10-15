import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindEvensOrOdds_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] numbers = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int lowerBound = numbers[0];
        int upperBound = numbers[1];

        String criteria = scanner.nextLine();

        Predicate<Integer> predicate = evenOddNumbers(criteria);

        List<Integer> result = IntStream.rangeClosed(lowerBound, upperBound)
                .mapToObj(Integer::valueOf)
                .filter(evenOddNumbers(criteria))
                .collect(Collectors.toList());

        result.stream().forEach(x -> printNumbers(x));
    }

    private static void printNumbers(Integer number) {
        System.out.print(number + " ");
    }

    private static Predicate<Integer> evenOddNumbers(String criteria ) {

        switch (criteria){
            case "even":
                return x -> x % 2 == 0;
            case "odd":
                return x -> x % 2 != 0;
            default:
                throw new NoSuchElementException("Wrong criteria.");
        }
    }
}
