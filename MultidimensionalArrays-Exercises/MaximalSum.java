import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine()
                        .split(" ")).mapToInt(Integer::parseInt)
                .toArray();

        int rows = input[0];
        int cols = input[1];

        int[][] matrix = new int[rows][cols];

        fillMatrix(scanner, matrix);

        int maxValue = Integer.MIN_VALUE;

        int[][] maxMatrix = new int[3][3];

        for (int row = 0; row < rows - 2; row++) {

            for (int col = 0; col < cols - 2; col++) {
                int currentValue = matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2] +
                        matrix[row + 1][col] + matrix[row + 1][col + 1] + matrix[row + 1][col + 2] +
                        matrix[row + 2][col] + matrix[row + 2][col + 1] + matrix[row + 2][col + 2];

                if (currentValue > maxValue) {
                    maxValue = currentValue;

                    maxMatrix = new int[][]{
                            {matrix[row][col], matrix[row][col + 1], matrix[row][col + 2]},
                            {matrix[row + 1][col], matrix[row + 1][col + 1], matrix[row + 1][col + 2]},
                            {matrix[row + 2][col], matrix[row + 2][col + 1], matrix[row + 2][col + 2]},
                    };
                }
            }
        }

        System.out.println("Sum = " + maxValue);
        printMatrix(maxMatrix);
    }

    private static void printMatrix(int[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] fillMatrix(Scanner scanner, int[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {
            int[] input = Arrays.stream(scanner.nextLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            matrix[row] = input;
        }

        return matrix;
    }
}
