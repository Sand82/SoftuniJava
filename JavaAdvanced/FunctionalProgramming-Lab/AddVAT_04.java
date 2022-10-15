import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddVAT_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> prices = Arrays.stream(scanner.nextLine()
                .split(", "))
                .map(Double::parseDouble)
                .map(x -> x =x * 1.2)
                .collect(Collectors.toList());

        System.out.println("Prices with VAT:");

        prices.forEach(x ->System.out.println(String.format("%.2f", x)));
    }
}
