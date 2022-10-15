import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateForNames_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Predicate<String> predicate = x -> x.length() <= n;
        Consumer<String> consumer = x -> System.out.println(x);

        List<String> names = Arrays.stream(scanner.nextLine()
                .split(" "))
                .filter(predicate)
                .collect(Collectors.toList());

        names.stream().forEach(consumer);
    }
}
