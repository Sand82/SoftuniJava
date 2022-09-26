import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> phoneBook = new HashMap<>();

        String [] input = scanner.nextLine().split("-");

        while(!input[0].equals("search")){

            String name = input[0];
            String number = input[1];

            phoneBook.putIfAbsent(name, number);
            input = scanner.nextLine().split("-");
        }

        String searchName = scanner.nextLine();
        while (!searchName.equals("stop")){

            if (!phoneBook.containsKey(searchName)) {
                System.out.println(String.format("Contact %s does not exist.", searchName));
            }else {
                System.out.println(String.format("%s -> %s",searchName, phoneBook.get(searchName)));
            }
            searchName = scanner.nextLine();
        }
    }
}
