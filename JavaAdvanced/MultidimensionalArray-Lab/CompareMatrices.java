import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] firstMatrix = getFillMatrix(scanner);
        int[][] secondMatrix = getFillMatrix(scanner);

        Boolean isEqual = true;

        if (firstMatrix.length != secondMatrix.length) {
            isEqual = false;
        }else {
            for (int row = 0; row < firstMatrix.length; row++) {

                if (firstMatrix[row].length != secondMatrix[row].length) {
                    isEqual = false;
                    break;
                }

                for (int col = 0; col < firstMatrix.length; col++) {

                    if (firstMatrix[row][col] != secondMatrix[row][col]) {
                        isEqual = false;
                    }
                }
            }
        }

        System.out.println(isEqual == true ? "equal" : "not equal");
    }

    public static int[][] getFillMatrix(Scanner scanner) {
        String[] commands = scanner.nextLine().split(" ");

        int rols = Integer.parseInt(commands[0]);
        int cols = Integer.parseInt(commands[1]);

        int[][] matrix = new int[rols][cols];

        for (int row = 0; row < rols; row++) {
            int[] input = Arrays.stream(scanner.nextLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            matrix[row] = input;
        }

        return matrix;
    }
}
