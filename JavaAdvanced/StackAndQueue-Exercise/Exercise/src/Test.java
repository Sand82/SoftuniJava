import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int [][] matrix = new int[4][4];

        int startValue = 1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = startValue;
                startValue ++;
            }
        }


        printMatrix(matrix);
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
