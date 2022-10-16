package PointInRectangle_02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = readArray(scanner);

        Point A = new Point(coordinates[0], coordinates[1]);
        Point C = new Point(coordinates[2], coordinates[3]);

        Rectangle rectangle = new Rectangle(A, C);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            int[] tokens = readArray(scanner);

            Point checkPoint = new Point(tokens[0], tokens[1]);

            boolean result = rectangle.contains(checkPoint);

            System.out.println(result);
        }
    }

    private static int[] readArray(Scanner scanner) {

        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }


}
