import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int [][] matrix = getFillMatrix(scanner, n);

        String[] fisrtDiagonal = new String[n];
        String[] secondDiagonal = new String[n];

        int counterSecondDiagonal = n - 1;

        for (int row = 0; row < n; row++) {
            fisrtDiagonal[row] = String.valueOf(matrix[row][row]);
            secondDiagonal[row]= String.valueOf(matrix[counterSecondDiagonal][row]);
            counterSecondDiagonal --;
        }

        System.out.println(String.join(" ", fisrtDiagonal));
        System.out.println(String.join(" ", secondDiagonal));

    }

    private static int[][] getFillMatrix(Scanner scanner, int n) {
        int [][] matrix = new int[n][n];

        for (int row = 0; row < n; row++) {

            int [] input = Arrays.stream(scanner.nextLine()
                            .split(" ")).mapToInt(Integer::parseInt)
                    .toArray();

            matrix[row] = input;
        }

        return matrix;
    }
}
