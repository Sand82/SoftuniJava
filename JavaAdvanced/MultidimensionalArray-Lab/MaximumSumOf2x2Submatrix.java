import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOf2x2Submatrix {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String [] parameters = scanner.nextLine().split(", ");

        int rows = Integer.parseInt(parameters[0]);
        int cols = Integer.parseInt(parameters[1]);

        int [][] matrix = getFillMatrix(scanner, rows, cols);

        int biggestSum = Integer.MIN_VALUE;
        int firstRowFirstCol = 0;
        int firstRowSecondCol = 0;
        int secondRowFirstCol = 0;
        int secondRowSecondCol = 0;

        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {

                int currentSum = matrix[row][col] + matrix[row][col + 1] + matrix[row + 1][col] + matrix[row + 1][col +1];

                if (currentSum > biggestSum) {
                    biggestSum = currentSum;

                    firstRowFirstCol = matrix[row][col];
                    firstRowSecondCol = matrix[row][col + 1];
                    secondRowFirstCol = matrix[row + 1][col];
                    secondRowSecondCol = matrix[row + 1][col +1];
                }
            }
        }

        System.out.println(firstRowFirstCol + " " + firstRowSecondCol);
        System.out.println(secondRowFirstCol + " " + secondRowSecondCol);
        System.out.println(biggestSum);
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
