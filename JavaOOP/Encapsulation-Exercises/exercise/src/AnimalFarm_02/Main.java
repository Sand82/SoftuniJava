package AnimalFarm_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String chickenName = scanner.nextLine();

        int age = Integer.parseInt(scanner.nextLine());

        try{
            Chicken chicken = new Chicken(chickenName, age);

            System.out.println(chicken.toString());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

    }
}
