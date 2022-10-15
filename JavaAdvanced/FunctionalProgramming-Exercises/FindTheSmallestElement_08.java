import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FindTheSmallestElement_08 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int [] numbers = Arrays.stream(scanner.nextLine()
                .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);

        List<Integer> evenNumbers = Arrays.stream(numbers).filter(x -> x % 2 == 0).boxed().sorted(comparator).collect(Collectors.toList());
        List<Integer> oddNumbers = Arrays.stream(numbers).filter(x -> x % 2 != 0).boxed().sorted(comparator).collect(Collectors.toList());

        List<String> result = Stream.concat(evenNumbers.stream(), oddNumbers.stream()).map(String::valueOf)
                .collect(Collectors.toList());

        System.out.println(String.join(" ", result));
    }
}
