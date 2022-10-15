import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Car> cars = new ArrayList<>();

        while (n-- > 0) {

            String[] input = scanner.nextLine().split(" ");

            String model = input[0];
            int engineSpeed = Integer.parseInt(input[1]);
            int enginePower = Integer.parseInt(input[2]);
            int cargoWeight = Integer.parseInt(input[3]);
            String cargoType = input[4];
            List<Tire> tires = new ArrayList<>();

            for (int i = 5; i < input.length; i += 2) {

                double tire1Pressure = Double.parseDouble(input[i]);
                int tire1Age = Integer.parseInt(input[i + 1]);

                Tire tire = new Tire(tire1Age, tire1Pressure);
                tires.add(tire);
            }

            Engine engine = new Engine(enginePower, engineSpeed);
            Cargo cargo = new Cargo(cargoWeight, cargoType);

            Car currCars = new Car(model, engine, cargo, tires);

            cars.add(currCars);
        }

        String command = scanner.nextLine();

        List<Car> result;

        if (command.equals("fragile")) {
            result = cars.stream().filter(x -> x.getCargo().getType().equals(command)).collect(Collectors.toList());

            result = result.stream().filter(x -> x.getTires().stream().anyMatch(t -> t.getPressure() < 1.0)).collect(Collectors.toList());

        } else {
            result = cars.stream().filter(x -> x.getEngine().getPower() > 250).collect(Collectors.toList());
        }

        printCollection(result);
    }

    private static void printCollection(List<Car> result) {
        result.forEach(x -> System.out.println(x.getModel()));
    }
}