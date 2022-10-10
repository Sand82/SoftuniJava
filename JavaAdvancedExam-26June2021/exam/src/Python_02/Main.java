package Python_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static int snakeRow = 0;
    private static int snakeCol = 0;
    private static int pieceOfFood = 0;
    private static int snakeStartingLength = 1;
    private static boolean isSnakeDead = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        List<String> commands = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().replace(" ", "").toCharArray();

            for (int col = 0; col < n; col++) {

                matrix[row][col] = input[col];

                if (input[col] == 's') {
                    snakeRow = row;
                    snakeCol = col;
                }

                if (input[col] == 'f') {
                    pieceOfFood ++;
                    snakeStartingLength ++;
                }
            }
        }

        for (int i = 0; i < commands.size(); i++) {

            String command = commands.get(i);
            matrix[snakeRow][snakeCol] = '*';

            switch (command) {
                case "up":
                    if (snakeRow - 1 < 0) {

                        snakeRow = n;
                    }

                    checkCoordinates(-1, 0, matrix);
                    break;
                case "down":
                    if (snakeRow + 1 >= n) {
                        snakeRow = -1;
                    }

                    checkCoordinates(1, 0, matrix);
                    break;
                case "left":
                    if (snakeCol - 1 < 0) {
                        snakeCol = n;
                    }

                    checkCoordinates(0, -1, matrix);
                    break;
                case "right":
                    if (snakeCol + 1 >= n) {
                        snakeCol = -1;
                    }

                    checkCoordinates(0, 1, matrix);
                    break;
            }

            if (pieceOfFood == 0 || isSnakeDead) {
                break;
            }
        }

        if (pieceOfFood == 0){
            System.out.printf("You win! Final python length is %d", snakeStartingLength);
        }else if (isSnakeDead){
            System.out.println("You lose! Killed by an enemy!");
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.", pieceOfFood);
        }
        //printMatrix(matrix);
    }

    private static void checkCoordinates(int rowModifier, int colModifier, char[][] matrix) {

        int currRow = snakeRow + rowModifier;
        int currCol = snakeCol + colModifier;

        if (matrix[currRow][currCol] == 'f') {
            pieceOfFood --;
        }else if(matrix[currRow][currCol] == 'e') {
            isSnakeDead = true;
        }
        snakeRow = currRow;
        snakeCol = currCol;

        matrix[snakeRow][snakeCol] = 's';
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
