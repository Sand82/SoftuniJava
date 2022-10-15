import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split(" ");

        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String [][] matrix = new String[rows][cols];

        matrix = fillMatrix(scanner, matrix);

        input = scanner.nextLine().split(" ");

        String someString = " ";

        while (!input[0].equals("END")){

            if (input.length != 5 ) {
                System.out.println("Invalid input!");
                input = scanner.nextLine().split(" ");
                continue;
            }

            int firstRow = Integer.parseInt(input[1]);
            int firstCol = Integer.parseInt(input[2]);
            int secondRow = Integer.parseInt(input[3]);
            int secondCol = Integer.parseInt(input[4]);

            if (cordinateValidator(rows, cols, firstRow, firstCol) &&
                    cordinateValidator(rows, cols, secondRow, secondCol)) {

                String firstValueToSwap = matrix[firstRow][firstCol];
                String secondValueToSwap = matrix[secondRow][secondCol];

                String currentValue = firstValueToSwap;
                matrix[firstRow][firstCol] = secondValueToSwap;
                matrix[secondRow][secondCol] = currentValue;

                printMatrix(matrix);
            }else {
                System.out.println("Invalid input!");
            }

            input = scanner.nextLine().split(" ");
            someString = input[0];
        }
    }

    private static void printMatrix(String[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean cordinateValidator(int rows, int cols, int row, int col) {

        Boolean isValidCordinate = false;

        if (row >= 0 && row < rows && col <= cols && col < cols) {
            isValidCordinate = true;
        }

        return isValidCordinate;
    }

    private static String[][] fillMatrix(Scanner scanner, String[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            String[] input = scanner.nextLine().split(" ");
            matrix[row] = input;
        }

        return matrix;
    }
}
