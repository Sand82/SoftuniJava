import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] parameters = Arrays.stream(scanner.nextLine()
                .split(" ")).mapToInt(Integer::parseInt)
                .toArray();

        int rows = parameters[0];
        int cols = parameters[1];

        int[][] matrix = getReadMtrix(scanner, rows, cols);

        int numberToFind = scanner.nextInt();

        List<String> result = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                int elementValue = matrix[row][col];

                if (elementValue == numberToFind) {

                    String elementPosition = row + " " + col;
                    System.out.println(elementPosition);
                    result.add(elementPosition);
                }
            }
        }

        if (result.size() == 0) {
            System.out.println("not found");
        }
    }

    private static int[][] getReadMtrix(Scanner scanner, int rows, int cols) {
        int [][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int [] input = Arrays.stream(scanner.nextLine()
                    .split( " ")).mapToInt(Integer::parseInt)
                    .toArray();
            matrix[row] = input;
        }

        return  matrix;
    }
}
