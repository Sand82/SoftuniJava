import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> emailBook = new HashMap<>();




        while (true){

            String name = scanner.nextLine();

            if (name.equals("stop")) {
                break;
            }
            String[] emailParts = scanner.nextLine().split("\\.");

            String element = emailParts[emailParts.length - 1].toLowerCase();

            if (!element.equals("us") &&
                !element.equals("uk") &&
                !element.equals("com")
                 ){

                emailBook.putIfAbsent(name, String.join(".", emailParts));
            }

        }

        for (var items: emailBook.entrySet()) {
            System.out.printf(String.format("%s -> %s %n", items.getKey(), items.getValue()));
        }
    }
}
