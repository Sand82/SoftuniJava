package Selling_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int playerRow;
    private static int playerCol;
    private static int playerMoney = 0;
    private static int n;
    private static Boolean isOutOfBakery = false;
    private static List<String> pillars;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {
            matrix[row] = scanner.nextLine().toCharArray();
        }

        pillars = setElementsPosition(matrix);

        while (true) {

            String direction = scanner.nextLine();

            if (direction.equals("left")) {

                goToPosition(0, -1, matrix);

            } else if (direction.equals("right")) {
                goToPosition(0, 1, matrix);

            } else if (direction.equals("up")) {
                goToPosition(-1, 0, matrix);

            } else if (direction.equals("down")) {
                goToPosition(1, 0, matrix);
            }

            if (isOutOfBakery) {
                matrix[playerRow][playerCol] = '-';
                break;
            }

            if (playerMoney >= 50) {
                break;
            }
        }

        if (playerMoney >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }

        if (isOutOfBakery) {
            System.out.println("Bad news, you are out of the bakery.");
        }

        System.out.println("Money: " + playerMoney);

        printMatrix(matrix);
    }

    private static void goToPosition(int rowModifier, int colModifier, char[][] matrix) {

        int currRow = playerRow + rowModifier;
        int curCol = playerCol + colModifier;

        if (!boundaryValidator(currRow, curCol)) {
            isOutOfBakery = true;
        } else if (matrix[currRow][curCol] == 'O') {

            for (int i = 0; i < pillars.size(); i++) {
                int[] tokens = Arrays.stream(pillars.get(i)
                                .split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int pillarRow = tokens[0];
                int pillarCol = tokens[1];

                if (pillarRow != currRow && pillarCol != curCol) {

                    setMatrixCordinates(pillarRow, pillarCol, matrix);
                } else {
                    matrix[pillarRow][pillarCol] = '-';
                }
            }
        } else if (Character.isDigit(matrix[currRow][curCol])) {
            playerMoney += Integer.parseInt(String.valueOf(matrix[currRow][curCol]));

            setMatrixCordinates(currRow, curCol, matrix);
        }else if ((matrix[currRow][curCol] == '-')){

            setMatrixCordinates(currRow, curCol, matrix);
        }
    }

    private static void setMatrixCordinates(int currRow, int currCol, char[][] matrix) {
        matrix[currRow][currCol] = 'S';
        matrix[playerRow][playerCol] = '-';
        playerRow = currRow;
        playerCol = currCol;
    }

    private static boolean boundaryValidator(int currRow, int currCol) {
        return currRow >= 0 && currRow < n && currCol >= 0 && currCol < n;
    }

    private static List<String> setElementsPosition(char[][] matrix) {

        List<String> pillars = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

                if (matrix[row][col] == 'S') {
                    playerRow = row;
                    playerCol = col;
                }

                if (matrix[row][col] == 'O') {
                    pillars.add(row + " " + col);
                }
            }
        }
        return pillars;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
