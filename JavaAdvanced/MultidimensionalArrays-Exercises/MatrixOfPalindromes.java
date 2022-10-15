import java.util.Scanner;

public class MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split(" ");

        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String [][] matrix  = new String [rows][cols];

        for (int row = 0; row < rows; row++) {

            char equalSymbol = (char)('a' + row);

            for (int col = 0; col < cols; col++) {

                char fastSymbol = (char)(equalSymbol + col);
                matrix[row][col] = ""+ (equalSymbol) + fastSymbol + equalSymbol;
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
