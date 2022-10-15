import java.util.*;
import java.util.stream.Collectors;

public class Largest3Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer > numbers = new ArrayList<>();

        numbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).sorted((a,b)-> b.compareTo(a)).collect(Collectors.toList());

        for (int i = 0; i < 3; i++) {

            if (i >= numbers.size()) {
                break;
            }

            int num =  numbers.get(i);

            System.out.printf(String.format("%d ", num));
        }
    }
}
