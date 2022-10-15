package GenericBoxOfInteger_03;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Box<Double> box = new Box<>();

       for(int i = 0; i < n ; i++){

            String input = scanner.nextLine();

            box.addItem(Double.parseDouble(input));
        }

        Double compareString =Double.parseDouble(scanner.nextLine());

        long result = box.CompareElements(compareString);

        System.out.println(result);
    }
}
