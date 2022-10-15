import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Engine> enginesCatalogue = new HashMap<>();

        List<Car> cars = new ArrayList<>();

        while (n-- > 0) { //"{Model} {Power} {Displacement} {Efficiency}

            String[] input = scanner.nextLine().split(" ");

            String model = input[0];
            int power = Integer.parseInt(input[1]);
            int displacement;
            String efficiency;

            Engine engine;

            if (input.length < 4) {
                if (!Character.isDigit(input[2].charAt(0))) {
                    efficiency = input[2];
                    engine = new Engine(model, power, efficiency);
                }else{
                    displacement = Integer.parseInt(input[2]);
                    engine = new Engine(model, power, displacement);
                }
            }else {
                displacement = Integer.parseInt(input[2]);
                efficiency = input[3];
                engine = new Engine(model, power, displacement, efficiency);
            }

            enginesCatalogue.putIfAbsent(model, engine);
        }

        int m = Integer.parseInt(scanner.nextLine());

        while(m-- > 0){
            
            String [] input = scanner.nextLine().split(" ");

            String carModel = input[0];
            String carEngine = input[1];
            int weight  = 0;
            String color = "n/a";

            Car car = null;

            Engine neededEngine = enginesCatalogue.get(carEngine);

            if (input.length == 2) { //VolkswagenPolo V7-54 1200 Yellow
                car = new Car(carModel, neededEngine);
            }else if(input.length == 3){

                if (Character.isDigit(input[2].charAt(0))) {
                    int carWeight = Integer.parseInt(input[2]);

                    car = new Car(carModel, neededEngine, carWeight);
                }else {
                    color = input[2];

                    car = new Car(carModel, neededEngine, color);
                }

            }else if(input.length == 4){
                weight = Integer.parseInt(input[2]);
                color = input[3];

                car = new Car(carModel, neededEngine, weight, color);
            }

            cars.add(car);
        }

        cars.forEach(x -> System.out.println(x.toString()));
    }
}