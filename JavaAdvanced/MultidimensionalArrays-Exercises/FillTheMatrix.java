import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        String [] input = scanner.nextLine().split(", ");
        int n = Integer.parseInt(input[0]);
        String methodType = input[1];

        int matrixContent = 1;

        int matrix[][] = new int[n][n];

        switch (methodType){
            case  "A":
                matrix = fillMatrixMethodA(n, matrixContent, matrix);
                break;
            case "B":
                matrix = fillMatrixMethodB(n, matrixContent, matrix);
                break;
        }

        printMatrix(matrix);

    }

    private static int[][] fillMatrixMethodB(int n, int matrixContent, int[][] matrix) {

        for (int row = 0; row < n; row++) {

            int colCounter = n - 1;

            for (int col = 0 ; col < n; col++ ) {

                if (row % 2 == 0) {
                    matrix[col][row] = matrixContent;
                }else {
                    matrix[colCounter][row] = matrixContent;
                }
                colCounter--;
                matrixContent ++;
            }
        }

        return matrix;
    }

    private static int[][] fillMatrixMethodA(int n, int matrixContent, int [][] matrix) {

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {
                matrix[col][row] = matrixContent;
                matrixContent ++;
            }
        }
        return matrix;
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
