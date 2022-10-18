import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsumerPrint_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Consumer<String> consumer = x-> System.out.println(x);

        List<String> names = Arrays.stream(scanner.nextLine()
                .split(" "))
                .collect(Collectors.toList());

        names.stream().forEach(consumer);
    }
}