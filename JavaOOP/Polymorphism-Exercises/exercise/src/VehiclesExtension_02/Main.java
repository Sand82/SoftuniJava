package VehiclesExtension_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] carParts = getParts(scanner);
        Car car = new Car(Double.parseDouble(carParts[1]), Double.parseDouble(carParts[2]), Integer.parseInt(carParts[3]));

        String[] truckParts = getParts(scanner);
        Truck truck = new Truck(Double.parseDouble(truckParts[1]), Double.parseDouble(truckParts[2]), Integer.parseInt(truckParts[3]));

        String [] busParts = getParts(scanner);
        Bus bus = new Bus(Double.parseDouble(busParts[1]), Double.parseDouble(busParts[2]), Integer.parseInt(busParts[3]));

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

                    } else if(Truck.class.getSimpleName().equals(typeVehicle)) {
                        truck.driving(methodArgument);

                    } else if(Bus.class.getSimpleName().equals(typeVehicle)){
                        bus.driving(methodArgument);
                    }
                    break;

                case "Refuel":

                    if (Car.class.getSimpleName().equals(typeVehicle)) {
                        car.refueling(methodArgument);

                    } else if(Truck.class.getSimpleName().equals(typeVehicle)) {
                        truck.refueling(methodArgument);

                    } else if(Bus.class.getSimpleName().equals(typeVehicle)){
                        bus.refueling(methodArgument);
                    }

                    break;
                case"DriveEmpty":
                    bus.driveEmpty(methodArgument);
                        break;
            }
        }
        System.out.printf("Car: %.2f%n", car.getQuantity());
        System.out.printf("Truck: %.2f%n", truck.getQuantity());
        System.out.printf("Bus: %.2f%n", bus.getQuantity());
    }

    private static String[] getParts(Scanner scanner) {

        String [] arr = scanner.nextLine().split("\\s+");
        return arr;
    }
}
