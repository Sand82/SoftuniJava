import java.util.*;
import java.util.stream.Collector;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Set<String> elements = new TreeSet<>();

        for (int i = 0; i < n; i++) {

            String [] input = scanner.nextLine().split(" ");

            Collections.addAll(elements, input);
        }

        System.out.println(String.join(" ", elements));
    }
}
