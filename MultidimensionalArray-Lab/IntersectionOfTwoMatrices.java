import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer rows =Integer.parseInt(scanner.nextLine());
        Integer cols =Integer.parseInt(scanner.nextLine());


        char [][] firstMatrix = getPrintMatrix(scanner, rows, cols);
        char [][] secondtMatrix = getPrintMatrix(scanner, rows, cols);

        char [][] matrixResult = new char[rows][cols];

        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                if (firstMatrix[row][col]== secondtMatrix[row][col]){
                    matrixResult[row][col] = firstMatrix[row][col];
                }else {
                    matrixResult[row][col] = '*';
                }
            }
        }

        getPrintMatrix(matrixResult);

    }

    private static void getPrintMatrix(char[][] matrixResult) {

        for (int row = 0; row < matrixResult.length; row++) {

            for (int col = 0; col < matrixResult[row].length; col++) {

                System.out.print(matrixResult[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] getPrintMatrix(Scanner scanner, Integer rows, Integer cols) {

        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {

            matrix[row]= scanner.nextLine()
                    .replaceAll("\\s+", "")
                    .toCharArray();

        }

        return  matrix;
    }
}
