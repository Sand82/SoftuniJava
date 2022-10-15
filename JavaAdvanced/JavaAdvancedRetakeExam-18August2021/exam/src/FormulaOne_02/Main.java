package FormulaOne_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int playerRow = 0;
    private static int playerCol = 0;
    private static List<String> listCommands = new ArrayList<>();

    private static boolean isWinTheRace = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int commandsCount = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {

            char[] charInput = scanner.nextLine().toCharArray();

            for (int col = 0; col < n; col++) {

                matrix[row][col] = charInput[col];

                if (charInput[col] == 'P') {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }

        matrix[playerRow][playerCol] = '.';

        for (int i = 0; i < commandsCount; i++) {
            listCommands.add(scanner.nextLine());
        }

        for (int i = 0; i < listCommands.size(); i++) {

            String command = listCommands.get(i);

            switch (command) {
                case "up":
                    if (playerRow - 1 < 0) {

                        playerRow = n;
                    }

                    checkCoordinates(-1, 0, matrix, i, command, "down");
                    break;
                case "down":
                    if (playerRow + 1 >= n) {
                        playerRow = -1;
                    }

                    checkCoordinates(1, 0, matrix, i, command, "up");
                    break;
                case "left":
                    if (playerCol - 1 < 0) {
                        playerCol = n;
                    }

                    checkCoordinates(0, -1, matrix, i, command, "right");
                    break;
                case "right":
                    if (playerCol + 1 >= n) {
                        playerCol = -1;
                    }

                    checkCoordinates(0, 1, matrix, i, command, "left");
                    break;
            }

        }

        matrix[playerRow][playerCol] = 'P';

        if (isWinTheRace) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }

        printMatrix(matrix);
    }

    private static void checkCoordinates(int rowModifier, int colModifier, char[][] matrix, int index, String bonus, String trap) {

        int currRow = rowModifier + playerRow;
        int currCol = colModifier + playerCol;

        if (matrix[currRow][currCol] == 'T') {

            currRow = playerRow;
            currCol = playerCol;

        } else if (matrix[currRow][currCol] == 'B') {

            listCommands.add(index + 1, bonus);
        } else if (matrix[currRow][currCol] == 'F') {

            isWinTheRace = true;
        }
        playerRow = currRow;
        playerCol = currCol;
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
