package BorderControl_05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Identifiable> persons = new ArrayList<>();

        while(!input.equals("End")){

            String [] inputParts = input.split("\\s+");

            Identifiable person = inputParts.length == 3
                    ? new Citizen(inputParts[0], Integer.parseInt(inputParts[1]), inputParts[2])
                    : new Robot(inputParts[0], inputParts[1]);

            persons.add(person);

            input = scanner.nextLine();
        }

        String fakeIdPart = scanner.nextLine();

        persons.stream().filter(p -> p.getId().endsWith(fakeIdPart))
                .forEach(p -> System.out.println(p.getId()));
    }
}
