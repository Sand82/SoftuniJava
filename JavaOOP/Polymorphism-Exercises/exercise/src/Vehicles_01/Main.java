package Vehicles_01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] carParts = getParts(scanner);
        Car car = new Car(Double.parseDouble(carParts[1]), Double.parseDouble(carParts[2]));

        String[] truckParts = getParts(scanner);
        Truck truck = new Truck(Double.parseDouble(truckParts[1]), Double.parseDouble(truckParts[2]));

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            String[] commandParts = scanner.nextLine().split("\\s+");

            String methodCommand = commandParts[0];
            String typeVehicle = commandParts[1];
            double methodArgument = Double.parseDouble(commandParts[2]);

            switch (methodCommand) {

                case "Drive":
                    if (Car.class.getSimpleName().equals(typeVehicle)) {
                        car.driving(methodArgument);
                    } else {
                        truck.driving(methodArgument);
                    }
                    break;

                case "Refuel":
                    if (Car.class.getSimpleName().equals(typeVehicle)) {
                        car.refueling(methodArgument);
                    } else {
                        truck.refueling(methodArgument);
                    }
                    break;
            }
        }
        System.out.printf("Car: %.2f%n", car.getQuantity());
        System.out.printf("Truck: %.2f%n", truck.getQuantity());
    }

    private static String[] getParts(Scanner scanner) {

        String [] arr = scanner.nextLine().split("\\s+");
        return arr;
    }
}
