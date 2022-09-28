import javax.imageio.ImageTranscoder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomMinFunction_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine()
                        .split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

//        Function<List<Integer>, Integer> minNumber = x -> {
//            int minValue = Integer.MAX_VALUE;
//
//            for (var number : numbers) {
//                if (number < minValue) {
//                    minValue = number;
//                }
//            }
//
//            return minValue;
//        };

        int value = minValue(numbers);

        System.out.println(value);
    }

    private static Integer minValue(List<Integer> numbers) {

        int minValue = Integer.MAX_VALUE;

        for (var number : numbers) {
            if (number < minValue) {
                minValue = number;
            }
        }

        return minValue;
    }
}

