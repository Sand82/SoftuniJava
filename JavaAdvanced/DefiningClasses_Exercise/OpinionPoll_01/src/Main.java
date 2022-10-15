import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Person> persons = new ArrayList<>();

        while (n-- != 0){
            String [] input = scanner.nextLine().split(" ");

            String name = input[0];
            int age = Integer.parseInt(input[1]);

            if (age > 30) {
                Person person = new Person(name ,age);
                persons.add(person);
            }
        }

        persons.stream().sorted((p1, p2)-> p1.getName().compareTo(p2.getName())).forEach(x -> System.out.println(x.toString()));

    }
}