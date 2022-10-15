import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Car> result = new ArrayList<>();

        while (n-- != 0) {

            String [] input = scanner.nextLine().split(" ");

            String carBrand = input[0];
            String carModel;
            int carHorsPower;

            Car car;

            if (input.length > 1) {
                carModel = input[1];
                carHorsPower = Integer.parseInt(input[2]);
                car = new Car(carBrand, carModel, carHorsPower);
            }else {
                car = new Car(carBrand);
            }

            result.add(car);
        }

        for (var item: result) {
            System.out.println(item.carInfo());
        }
    }
}
