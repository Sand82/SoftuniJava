package MouseAndCheese_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int mouseRow = 0;
    private static int mouseCol = 0;
    private static int countOfEatenCheese = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().toCharArray();

            for (int col = 0; col < n; col++) {
                matrix[row][col] = input[col];

                if (input[col] == 'M') {
                    mouseRow = row;
                    mouseCol = col;
                }
            }
        }

        String commandInput = scanner.next();

        boolean isOutOfBounds = false;

        while (!commandInput.equals("end")) {

            String direction = commandInput;

            matrix[mouseRow][mouseCol] = '-';

            switch (direction) { // up,right,down,down,left
                case "up":
                    if (mouseRow - 1 >= 0) {
                        checkPosition(-1, 0, matrix);
                    } else {
                        isOutOfBounds = true;
                    }

                    break;
                case "down":
                    if (mouseRow + 1 < n) {
                        checkPosition(1, 0, matrix);
                    } else {
                        isOutOfBounds = true;
                    }

                    break;
                case "right":
                    if (mouseCol + 1 < n) {
                        checkPosition(0, 1, matrix);
                    } else {
                        isOutOfBounds = true;
                    }

                    break;
                case "left":
                    if (mouseCol - 1 >= 0) {
                        checkPosition(0, -1, matrix);
                    } else {
                        isOutOfBounds = true;
                    }
                    break;
            }

            if (isOutOfBounds) {
                break;
            }

            commandInput = scanner.next();
        }

        if (isOutOfBounds) {

            System.out.println("Where is the mouse?");
        }

        if (countOfEatenCheese < 5) {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n",
                    5 - countOfEatenCheese);
        }else {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", countOfEatenCheese);
        }

        printMatrix(matrix);
    }

    private static void checkPosition(int rowModifier, int colModifier, char[][] matrix) {

        int currRow = rowModifier + mouseRow;
        int currCol = colModifier + mouseCol;

        if (matrix[currRow][currCol] == 'c') {

            countOfEatenCheese++;
        } else if (matrix[currRow][currCol] == 'B') {

            matrix[currRow][currCol] = '-';

            if (rowModifier != 0) {
                currRow += rowModifier;
            }else {
                currCol += colModifier;
            }

            if (matrix[currRow][currCol] == 'c') {
                countOfEatenCheese++;
            }
        }

        mouseRow = currRow;
        mouseCol = currCol;

        matrix[mouseRow][mouseCol] = 'M';
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
