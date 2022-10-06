package TreasureHunt_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static int playerRow = 0;
    private static int playerCol = 0;

    private static int treasureRow = 0;
    private static int treasureCol = 0;

    private static boolean isTreasureFound = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] coordinate = scanner.nextLine().split(" ");

        int fieldRow = Integer.parseInt(coordinate[0]);
        int fieldCol = Integer.parseInt(coordinate[1]);

        char[][] matrix = new char[fieldRow][fieldCol];

        for (int row = 0; row < fieldRow; row++) {
            List<Character> inputArray = Arrays.stream(scanner.nextLine()
                            .split(" "))
                    .map(e -> e.charAt(0))
                    .collect(Collectors.toList());

            for (int col = 0; col < fieldCol; col++) {

                char currChar = inputArray.get(col);
                matrix[row][col] = currChar;

                if (inputArray.get(col) == 'Y') {
                    playerRow = row;
                    playerCol = col;
                }

                if (inputArray.get(col) == 'X') {
                    treasureRow = row;
                    treasureCol = col;
                }
            }
        }
        List<String> directions = new ArrayList<>();
        String command = scanner.next();

        while (!command.equals("Finish")) {

            directions.add(command);

            switch (command) {
                case "up":
                    if (playerRow - 1 >= 0 && matrix[playerRow - 1][playerCol] != 'T') {
                        setCoordinate(-1, 0, matrix);
                    }
                    break;
                case "down":
                    if (playerRow + 1 < fieldRow && matrix[playerRow + 1][playerCol] != 'T') {
                        setCoordinate(+1, 0, matrix);
                    }
                    break;
                case "left":
                    if (playerCol - 1 >= 0 && matrix[playerRow][playerCol - 1] != 'T') {
                        setCoordinate(0, -1, matrix);
                    }
                    break;
                case "right":
                    if (playerCol + 1 < fieldCol && matrix[playerRow][playerCol + 1] != 'T') {
                        setCoordinate(0, +1, matrix);
                    }
                    break;
            }
//            System.out.println();
//            printMatrix(matrix);

            command = scanner.next();
        }

        if (treasureRow == playerRow && treasureCol == playerCol) {
            System.out.println("I've found the treasure!");
            System.out.print("The right path is " + String.join(", ", directions));
        } else {
            System.out.println("The map is fake!");
        }
    }

    private static void setCoordinate(int rowNum, int colNum, char[][] matrix) {

        matrix[playerRow][playerCol] = '-';
        playerRow += rowNum;
        playerCol += colNum;
        matrix[playerRow][playerCol] = 'Y';
        matrix[treasureRow][treasureCol] = 'X';
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