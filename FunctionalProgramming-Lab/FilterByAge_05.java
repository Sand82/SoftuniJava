import com.sun.jdi.connect.Connector;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterByAge_05 {

    public static class Person {
        private int age;
        private String name;

        public  Person(String name, int age){
            this.name = name;
            this.age = age;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> personInfo = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(", ");

            String name = input[0];
            int age = Integer.parseInt(input[1]);

            personInfo.put(name, age);
        }

        String condition = scanner.nextLine();

        Integer ageConditional = Integer.parseInt(scanner.nextLine());

        String typeOfPrint = scanner.nextLine();
    }

    private static Predicate<Integer> filterByAge(String condition, Integer ageConditions) {

        switch (condition) {
            case "older":
                return  x -> x >= ageConditions;
            case "younger":
                return  x -> x <= ageConditions;
            default:
                throw new NoSuchElementException("Invalid command!");
        }
    }
}
