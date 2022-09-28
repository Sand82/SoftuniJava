import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumNumbers_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine()
                        .split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int numArrSize = numbers.size();

        int sumNumbers = numbers.stream().reduce(0, (n, i) -> n + i);

        System.out.println(String.format("Count = %d",numArrSize));
        System.out.println(String.format("Sum = %d",sumNumbers));
    }
}
