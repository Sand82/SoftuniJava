import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][n];
        fillMatrix(n, scanner, matrix);

        int firstDiagonal = 0;
        int secondDiagonal = 0;

        for (int row = 0 , secondColCounter = n -1; row < matrix.length; row++, secondColCounter--) {
            firstDiagonal += matrix[row][row];
            secondDiagonal += matrix[row][secondColCounter];
        }

        System.out.println(Math.abs(firstDiagonal- secondDiagonal));
    }

    private static int[][] fillMatrix(int n, Scanner scanner, int [][] matrix) {

        for (int row = 0; row < n; row++) {
            int [] input = Arrays.stream(scanner.nextLine()
                    .split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            matrix[row] = input;
        }

        return matrix;
    }
}
