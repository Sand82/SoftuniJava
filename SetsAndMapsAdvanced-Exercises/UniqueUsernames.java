import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Set<String> collection = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            collection.add(input);
        }

        for (var item: collection) {
            System.out.println(item);
        }
    }
}
