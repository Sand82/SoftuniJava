package Google_07.src;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<People> peoples = new ArrayList<>();

        String[] input = scanner.nextLine().split(" ");

        while (!input[0].equals("End")) {

            String peopleName = input[0];
            String className = input[1];
            String objectName = input[2];

            People people = peoples.stream().filter(p -> p.getName().equals(peopleName)).findFirst().orElse(null);

            if (people == null) {
                people = new People(peopleName);
                peoples.add(people);
            }

            switch (className) {
                case "company":
                    String department = input[3];
                    double salary = Double.parseDouble(input[4]);
                    people.setCompany(new Company(objectName, department, salary));
                    break;
                case "pokemon":
                    String pokemonType = input[3];
                    people.getPokemons().add(new Pokemon(objectName, pokemonType));
                    break;
                case "car":
                    int carSpeed = Integer.parseInt(input[3]);
                    people.setCar(new Car(objectName, carSpeed));
                    break;
                case "parents":
                    String parentName = input[3];
                    people.getParents().add(new Parent(objectName, parentName));
                    break;
                case "children":
                    String birthDay = input[3];
                    people.getChildrens().add(new Children(objectName, birthDay));
                    break;
            }

            input = scanner.nextLine().split(" ");
        }

        String personToFind = scanner.nextLine();

         People person = peoples.stream().filter(p -> p.getName().equals(personToFind)).findFirst().orElse(null);

        if (person == null) {
            throw new NoSuchElementException("This person don't exist in the list.");
        }else {

            System.out.println(person.getName());
            System.out.println("Company:");
            if (person.getCompany() != null) {
                System.out.println(person.getCompany().toString());
            }
            System.out.println("Car:");
            if (person.getCar() != null) {
                System.out.println(person.getCar().toString());
            }

            System.out.println("Pokemon:");
            if (person.getPokemons().size() > 0) {
               person.getPokemons().forEach(p -> System.out.println(p.toString()));
            }

            System.out.println("Parents:");
            if (person.getParents().size() > 0) {
                person.getParents().forEach(p -> System.out.println(p.toString()));
            }

            System.out.println("Children:");
            if (person.getChildrens().size() > 0) {
                person.getChildrens().forEach(p -> System.out.println(p.toString()));
            }
        }
    }
}