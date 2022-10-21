package Animals_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> classesInfo = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("Beast!")) {

            classesInfo.add(input);

            input = scanner.nextLine();
        }

        for (int i = 0; i < classesInfo.size(); i += 2) {

            String animalType = classesInfo.get(i);
            String[] animalParts = classesInfo.get(i + 1).split("\\s+");

            String name = animalParts[0];
            int age = Integer.parseInt(animalParts[1]);
            String gender = animalParts[2];

            Animal animal = null;

            try {
                switch (animalType) {
                    case "Dog":
                        animal = new Dog(name, age, gender);
                        break;
                    case "Frog":
                        animal = new Frog(name, age, gender);
                        break;
                    case "Cat":
                        animal = new Cat(name, age, gender);
                        break;
                    case "Kitten":
                        animal = new Kitten(name, age);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(name, age);
                        break;
                }

                System.out.println(animal.toString());

            } catch (IllegalArgumentException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
}
