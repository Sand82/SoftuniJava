package Garden;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] dimension = scanner.nextLine().split("\\s+");

        int n = Integer.parseInt(dimension[0]);

        int m = Integer.parseInt(dimension[1]);

        int[][] matrix = new int[n][m];

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < m; col++) {
                matrix[row][col] = 0;
            }
        }

        String input = scanner.nextLine();

        List<String> flowers = new ArrayList<>();

        while (!input.equals("Bloom Bloom Plow")) {

            flowers.add(input);

            input = scanner.nextLine();
        }

        for (int i = 0; i < flowers.size(); i++) {

            String[] flower = flowers.get(i).split("\\s+");

            int flowerRow = Integer.parseInt(flower[0]);
            int flowerCol = Integer.parseInt(flower[1]);

            boolean isValidCoordinate = validateCoordinate(flowerRow, flowerCol, n, m);

            if (isValidCoordinate) {

                matrix[flowerRow][flowerCol] ++;

                //up
                for (int upRow = flowerRow + 1; upRow < n; upRow++) {
                    matrix[upRow][flowerCol]++;
                }

                //down
                for (int downRow = flowerRow - 1; downRow >= 0; downRow--) {
                    matrix[downRow][flowerCol]++;
                }

                //left

                for (int leftCol = flowerCol - 1; leftCol >= 0; leftCol--) {
                    matrix[flowerRow][leftCol]++;
                }

                //right

                for (int rightCol = flowerCol + 1; rightCol < m; rightCol++) {
                    matrix[flowerRow][rightCol]++;
                }

            } else {
                System.out.println("Invalid coordinates.");
            }
        }

        printMatrix(matrix);
    }

    private static boolean validateCoordinate(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
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
