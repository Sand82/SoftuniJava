import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WrongMeasurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = getFillMatrix(scanner, n);

        String[] values = scanner.nextLine().split(" ");

        int wrongRow = Integer.parseInt(values[0]);
        int wrongCol = Integer.parseInt(values[1]);

        int invalidValue = matrix[wrongRow][wrongCol];
        ArrayList<String> priviousInvalidPosition = new ArrayList<String>();

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                int currentValue = matrix[row][col];
                int rightValue = 0;

                if (invalidValue == currentValue) {

                    priviousInvalidPosition.add(row + " " + col);

                    if (directionValidator(n, matrix[row].length, row, col - 1) &&
                            !isPreviousInvalidValue(priviousInvalidPosition, row, col - 1)) {

                        rightValue += matrix[row][col - 1] == invalidValue ? 0 : matrix[row][col - 1];
                    }

                    if (directionValidator(n, matrix[row].length,row, col + 1) &&
                            !isPreviousInvalidValue(priviousInvalidPosition, row, col + 1)) {

                        rightValue += matrix[row][col + 1] == invalidValue ? 0 : matrix[row][col + 1];
                    }

                    if (directionValidator(n, matrix[row].length,row + 1, col) &&
                            !isPreviousInvalidValue(priviousInvalidPosition, row + 1, col)) {

                        rightValue += matrix[row + 1][col] == invalidValue ? 0 : matrix[row + 1][col];
                    }

                    if (directionValidator(n, matrix[row].length,row - 1, col) &&
                            !isPreviousInvalidValue(priviousInvalidPosition, row - 1, col)) {

                        rightValue += matrix[row - 1][col] == invalidValue ? 0 : matrix[row - 1][col];
                    }

                    matrix[row][col] = rightValue;
                }
            }
        }

        printMatrix(matrix);
    }

    private static boolean directionValidator(int rowCount, int colCount,int row, int col) {

        if (row >= 0 && row < rowCount && col >= 0 && col < colCount) {
            return true;
        }
        return false;
    }

    private static int[][] getFillMatrix(Scanner scanner, int n) {
        int[][] matrix = new int[n][n];

        for (int row = 0; row < n; row++) {

            int[] input = Arrays.stream(scanner.nextLine()
                            .split(" ")).mapToInt(Integer::parseInt)
                    .toArray();

            matrix[row] = input;
        }

        return matrix;
    }

    private static boolean isPreviousInvalidValue(ArrayList<String> priviousInvalidPosition, int row, int col) {


        for (int i = 0; i < priviousInvalidPosition.size(); i++) {

            String[] tokens = priviousInvalidPosition.get(i).split(" ");

            int currRow = Integer.parseInt(tokens[0]);
            int currCol = Integer.parseInt(tokens[1]);

            if (currRow == row && currCol == col) {
                return true;
            }
        }

        return false;
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
