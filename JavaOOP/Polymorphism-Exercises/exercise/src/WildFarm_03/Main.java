package WildFarm_03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] foodTokens = splitString(scanner.nextLine());
            String[] animalTokens = splitString(input);

            Animal animal = createAnimal(animalTokens);
            Food food = createFood(foodTokens);

            animal.makeSound();
            animal.eat(food);
            System.out.println(animal);

            input = scanner.nextLine();
        }
    }

    private static Food createFood(String[] foodTokens) {
        Food food = null;

        switch (foodTokens[0]) {
            case "Meat":

                food = new Meat(Integer.parseInt(foodTokens[1]));
                break;
            case "Vegetable":

                food = new Vegetable(Integer.parseInt(foodTokens[1]));
                break;

        }

        return food;
    }

    private static Animal createAnimal(String[] animalTokens) {

        String animalType = animalTokens[0];
        String animalName = animalTokens[1];
        Double animalWeight = Double.parseDouble(animalTokens[2]);
        String animalLivingRegion = animalTokens[3];

        Animal animal = null;

        switch (animalType) {

            case "Mouse":
                animal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Zebra":
                animal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Tiger":
                animal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Cat":
                animal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, animalTokens[4]);
                break;
        }

        return animal;
    }

    private static String[] splitString(String str) {
        return str.split("\\s+");
    }
}
