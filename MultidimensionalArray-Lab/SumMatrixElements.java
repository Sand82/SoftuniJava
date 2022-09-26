import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] parameters = scanner.nextLine().split(", ");

        int rows = Integer.parseInt(parameters[0]);
        int cols = Integer.parseInt(parameters[1]);

        int [][] matrix = getFillMatrix(scanner, rows, cols);

        int sum = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int number = matrix[row][col];
                sum += number;
            }
        }

        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }

    private static int[][] getFillMatrix(Scanner scanner, int rows, int cols) {
        int [][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {

            int [] input = Arrays.stream(scanner.nextLine()
                    .split(", ")).mapToInt(Integer::parseInt)
                    .toArray();

            matrix[row] = input;
        }

        return matrix;
    }
}
