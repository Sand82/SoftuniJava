import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListOfPredicates_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startPoint = 1;
        int endingPoin = Integer.parseInt(scanner.nextLine());

        int [] divisibleArray = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> numbers = IntStream
                .rangeClosed(startPoint, endingPoin)
                .boxed()
                .collect(Collectors.toList());

        Predicate<Integer> predicate = x-> {

            for (int i = 0; i < divisibleArray.length; i++) {

                if (x % divisibleArray[i] != 0 ) {
                    return false;
                }
            }
            return true;
        };

        List<String> result = numbers.stream().filter(predicate).map(String::valueOf).collect(Collectors.toList());
        result.forEach(x -> System.out.print(x + " "));
    }
}
