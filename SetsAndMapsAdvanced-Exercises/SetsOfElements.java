import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.ServiceConfigurationError;
import java.util.Set;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] setsLengthInput = scanner.nextLine().split(" ");

        int firstSetLength = Integer.parseInt(setsLengthInput[0]);
        int secondSetLength = Integer.parseInt(setsLengthInput[1]);

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        for (int i = 0; i < firstSetLength + secondSetLength; i++) {

            int number =Integer.parseInt(scanner.nextLine());

            if (i < firstSetLength - 1) {
                firstSet.add(number);
            }else {

                if (firstSet.contains(number)) {
                    secondSet.add(number);
                }
            }
        }

        for (var num: secondSet) {
            System.out.print(num + " ");
        }
    }
}
