package FoodShortage_04;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String [] input = scanner.nextLine().split("\\s+");

            String name = input[0];
            int age = Integer.parseInt(input[1]);

            if (input.length == 4) {

                String id = input[2];
                String dateOfBirth = input[3];

                Citizen citizen = new Citizen(name, age, id, dateOfBirth);

                buyers.putIfAbsent(name, citizen);

            }else if (input.length == 3) {

                String group = input[2];

                Rebel rebel = new Rebel(name, age, group);

                buyers.putIfAbsent(name, rebel);
            }

        }

        String names = scanner.nextLine();

        int result = 0;

        while(!names.equals("End")) {


            if (buyers.containsKey(names)) {

                Buyer currentBuyer = buyers.get(names);
                currentBuyer.buyFood();

                result += currentBuyer.getFood();
            }

            names = scanner.nextLine();
        }

        System.out.println(result);
    }
}
