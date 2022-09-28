import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheSmallestElement_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Integer> numbers = Arrays.stream(scanner.nextLine()
                .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Function<List<Integer>, Integer> index = x -> {
            int smallestIndex = -1;
            int minValue = Integer.MAX_VALUE;

            for (int i = 0; i < numbers.size(); i++) {

                if (numbers.get(i) <= minValue) {
                    smallestIndex = i;
                    minValue = numbers.get(i);
                }
            };
            return  smallestIndex;
        };

        System.out.println(index.apply(numbers));
    }
}
