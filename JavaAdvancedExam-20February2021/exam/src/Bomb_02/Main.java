package Bomb_02;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static int sapperRow = 0;
    private static int sapperCol = 0;
    private static int bombCount = 0;

    private static boolean isEndOfRoad = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<String> commandsList = Arrays.stream(scanner.nextLine().split(","))
                .collect(Collectors.toList());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().replace(" ", "").toCharArray();

            for (int col = 0; col < n; col++) {
                matrix[row][col] = input[col];

                if (input[col] == 's') {
                    sapperRow = row;
                    sapperCol = col;
                }

                if (input[col] == 'B') {
                    bombCount++;
                }
            }
        }

        for (int i = 0; i < commandsList.size(); i++) {

            String direction = commandsList.get(i);
            matrix[sapperRow][sapperCol] = '+';

            switch (direction) { // up,right,down,down,left
                case "up":
                    if (sapperRow - 1 >= 0) {
                        checkPosition(-1, 0, matrix);
                    }

                    break;
                case "down":
                    if (sapperRow + 1 < n) {
                        checkPosition(1, 0, matrix);
                    }

                    break;
                case "right":
                    if (sapperCol + 1 < n) {
                        checkPosition(0, 1, matrix);
                    }

                    break;
                case "left":
                    if (sapperCol - 1 >= 0) {
                        checkPosition(0, -1, matrix);
                    }

                    break;
            }

            matrix[sapperRow][sapperCol] = 's';

            if (bombCount == 0) {
                System.out.println("Congratulations! You found all bombs!");
                break;
            }

            if (isEndOfRoad) {
                System.out.printf("END! %d bombs left on the field%n", bombCount);
                break;
            }
        }

        if (!isEndOfRoad && bombCount > 0) {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)%n", bombCount, sapperRow, sapperCol);
        }

       // printMatrix(matrix);
    }

    private static void checkPosition(int rowModifier, int colModifier, char[][] matrix) {

        int currRow = sapperRow + rowModifier;
        int currCol = sapperCol + colModifier;

        if (matrix[currRow][currCol] == 'e') {
            isEndOfRoad = true;
        } else if (matrix[currRow][currCol] == 'B') {

            System.out.println("You found a bomb!");
            bombCount--;
        }
        sapperRow = currRow;
        sapperCol = currCol;
    }

    private static void printMatrix(char[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                System.out.print(matrix[row][col] + " ");
            }

            System.out.println();
        }
    }
}
