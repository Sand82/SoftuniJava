package CookingJourney_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int myRow = 0;
    private static int myCol = 0;
    private static int earnedMoney = 0;
    private static List<Integer> pillars = new ArrayList<>();
    private static boolean isOutOfTheStore = false;

    private static String finaleMessage;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().toCharArray();
            for (int col = 0; col < n; col++) {

                matrix[row][col] = input[col];

                if (input[col] == 'S') {
                    myRow = row;
                    myCol = col;
                }

                if (input[col] == 'P') {
                    pillars.add(row);
                    pillars.add(col);
                }
            }
        }

        String input = scanner.nextLine();

        while (true) {

            matrix[myRow][myCol] = '-';

            switch (input) { // up,right,down,down,left
                case "up":
                    if (myRow - 1 >= 0) {
                        checkPosition(-1, 0, matrix);
                    } else {
                        isOutOfTheStore = true;
                    }
                    break;
                case "down":
                    if (myRow + 1 < n) {
                        checkPosition(1, 0, matrix);
                    } else {
                        isOutOfTheStore = true;
                    }
                    break;
                case "right":
                    if (myCol + 1 < n) {
                        checkPosition(0, 1, matrix);
                    } else {
                        isOutOfTheStore = true;
                    }
                    break;
                case "left":
                    if (myCol - 1 >= 0) {
                        checkPosition(0, -1, matrix);

                    } else {
                        isOutOfTheStore = true;
                    }
                    break;
            }

            if (isOutOfTheStore) {
                finaleMessage = "Bad news! You are out of the pastry shop.";
                break;
            }

            if (earnedMoney >= 50) {
                finaleMessage = "Good news! You succeeded in collecting enough money!";
                break;
            }

            input = scanner.nextLine();
        }

        System.out.println(finaleMessage);
        System.out.printf("Money: %d%n", earnedMoney);

        printMatrix(matrix);
    }

    private static void checkPosition(int rowModifier, int colModifier, char[][] matrix) {

        int currRow = rowModifier + myRow;
        int currCol = colModifier + myCol;

        if (matrix[currRow][currCol] == 'P') {

            for (int i = 0; i < 4; i += 2) {

                int pillarRow = pillars.get(i);
                int pillarsCol = pillars.get(i + 1);

                if (pillarRow != currRow && pillarsCol != currCol) {

                    currRow = pillarRow;
                    currCol = pillarsCol;
                }else {
                    matrix[pillarRow][pillarsCol] = '-';
                }
            }

        } else if (Character.isDigit(matrix[currRow][currCol])) {
            earnedMoney += matrix[currRow][currCol] - 48;
        }

        myRow = currRow;
        myCol = currCol;
        matrix[myRow][myCol] = 'S';
    }

    private static void printMatrix(char[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
