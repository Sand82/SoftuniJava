import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AppliedArithmetics_04 {
    private static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        numbers = Arrays.stream(scanner.nextLine()
                        .split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("end")) {

            comandExecuted(input);

            input = scanner.nextLine();
        }
    }

    private static void comandExecuted(String input) {

        switch (input) {
            case "add":
                numbers = numbers.stream().map(x -> x + 1).collect(Collectors.toList());
                return;
            case "multiply":
                numbers = numbers.stream().map(x -> x * 2).collect(Collectors.toList());
                return;
            case "subtract":
                numbers = numbers.stream().map(x -> x - 1).collect(Collectors.toList());
                return;
            case "print":
                Consumer<Integer> consumer = x -> System.out.printf("%d ", x);
                numbers.stream().forEach(consumer);
                return;
            default:
                throw new NoSuchElementException("Invalid command");
        }
    }
}
