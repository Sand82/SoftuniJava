package BirthdayCelebrations_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Birthable> list = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] commands = input.split("\\s+");

            String name = commands[1];
            String date;

            switch (commands[0]) {
                case "Citizen":

                    int age = Integer.parseInt(commands[2]);
                    String number = commands[3];
                    date = commands[4];

                    Citizen citizen = new Citizen(name, age, number, date);
                    list.add(citizen);
                    break;
                case "Pet":
                    date = commands[2];

                    Pet pet = new Pet(name, date);
                    list.add(pet);
                    break;

            }

            input = scanner.nextLine();
        }

        String dateToFind = scanner.nextLine();

       list = list.stream().filter(p -> p.getBirthDate().endsWith(dateToFind)).collect(Collectors.toList());

        if (list.size() != 0) {

            list.forEach(p -> System.out.println(p.getBirthDate()));
        } else {

            System.out.println("<no output>");
        }
    }
}
