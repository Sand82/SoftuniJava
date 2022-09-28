import java.util.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterByAge_05 {
    public static class Person {
        private int age;
        private String name;

        Person(String name, int age) {
            this.name = name;
            this.age = age;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(", ");

            String name = input[0];
            int age = Integer.parseInt(input[1]);

            Person person = new Person(name, age);

            persons.add(person);
        }

        String condition = scanner.nextLine();
        int ageConditional = Integer.parseInt(scanner.nextLine());
        String typeOfPrint = scanner.nextLine();

        persons = filterByAge(persons, condition.equals("younger") ? x -> x.age <= ageConditional : x -> x.age >= ageConditional);

        Consumer<Person> consumer = getPrinter(typeOfPrint);

        persons.forEach(consumer);
    }

    private static Consumer<Person> getPrinter(String typeOfPrint) {
        switch (typeOfPrint) {
            case "name age":
                return x -> System.out.printf("%s - %d%n", x.name, x.age);
            case "name":
                return  x -> System.out.println(x.name);
            case "age":
                return  x -> System.out.println(x.age);
            default:
                throw new NoSuchElementException("Missing condition");
        }
    }

    private static List<Person> filterByAge(List<Person> persons, Predicate<Person> predicate) {
        return persons.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
