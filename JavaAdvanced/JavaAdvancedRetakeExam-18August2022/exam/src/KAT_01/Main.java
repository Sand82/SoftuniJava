package KAT_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> licensedPlate = Arrays.stream(scanner.nextLine() // -> queue
                        .split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> cars = new ArrayDeque<>(); // stack

        Arrays.stream(scanner.nextLine()
                        .split(", "))
                .map(Integer::parseInt)
                .forEach(cars::push);

        int numberRegisteredCars = 0;
        int daysForRegistrations = 0;

        while (!licensedPlate.isEmpty() && !cars.isEmpty()) {
            int currPlates = licensedPlate.poll();
            int currCars = cars.pop();

            int difference = 0;
            int completeCars = 0;

            if (currPlates > currCars * 2) {
                difference = currPlates - currCars * 2;
                licensedPlate.offer(difference);

                completeCars = currCars;
            } else if (currPlates < currCars * 2) {
                difference = currCars - currPlates / 2;
                cars.offer(difference);

                completeCars = currPlates / 2;
            } else if (currPlates == currCars * 2) {
                completeCars = currCars;
            }
            numberRegisteredCars += completeCars;
            daysForRegistrations++;
        }

        System.out.printf("%s cars were registered for %s days!%n",numberRegisteredCars, daysForRegistrations);

        int carsWithoutRegistration = 0;
        int freeLicensedPlate = 0;

        String result = "";

        if (cars.isEmpty() && ! licensedPlate.isEmpty()) {
            freeLicensedPlate = foreachColection(licensedPlate);
            result = String.format("%d license plates remain!", freeLicensedPlate);
        }else if(licensedPlate.isEmpty() && !cars.isEmpty()){
            carsWithoutRegistration = foreachColection(cars);
            result = String.format("%d cars remain without license plates!", carsWithoutRegistration);
        }else {
            result = String.format("Good job! There is no queue in front of the KAT!");
        }

        System.out.println(result);
    }

    private static Integer foreachColection(ArrayDeque<Integer> collection) {

        int result = 0;
        for (Integer integer : collection) {
            result += integer;
        }

        return result;
    }
}
