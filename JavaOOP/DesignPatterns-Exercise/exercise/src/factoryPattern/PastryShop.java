package factoryPattern;

import java.util.Scanner;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split("\\s+");

        String cakeType = input[0];
        double diameter =Double.parseDouble(input[1]);
        double price =Double.parseDouble(input[2]);
        int peaces = Integer.parseInt(input[3]);

        Cake cakeFactory = CakeFactory.createCake(cakeType, diameter, price, peaces);
    }
}
