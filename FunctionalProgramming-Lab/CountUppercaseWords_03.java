import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountUppercaseWords_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> words = Arrays.stream((scanner.nextLine().split(" ")))
            .filter(x -> Character.isUpperCase(x.charAt(0)))
            .collect(Collectors.toList());

        int wortsSize = words.size();

        System.out.println(wortsSize);

        words.forEach(w -> System.out.println(w));
    }
}
