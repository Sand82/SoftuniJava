package StickyFingers_03;

import java.util.Scanner;

public class Main {
    private static int dillingerRow = 0;
    private static int dillingerCol = 0;

    private static int stolenMoney = 0;
    private static boolean isDillingerOnTheJail = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        String[] command = scanner.nextLine().split(",");

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().replace(" ", "").toCharArray();

            for (int col = 0; col < n; col++) {
                matrix[row][col] = input[col];

                if (input[col] == 'D') {
                    dillingerRow = row;
                    dillingerCol = col;
                }
            }
        }

        for (int i = 0; i < command.length; i++) {
            String direction = command[i];

            boolean wrongDirection = false;

            switch (direction) { // up,right,down,down,left
                case "up":
                    if (dillingerRow - 1 >= 0) {
                        checkPosition(-1, 0, matrix);
                    }else {
                        wrongDirection = true;
                    }
                    break;
                case "down":
                    if (dillingerRow + 1 < n) {
                        checkPosition(1, 0, matrix);
                    }else {
                        wrongDirection = true;
                    }
                    break;
                case "right":
                    if (dillingerCol + 1 < n) {
                        checkPosition(0, 1, matrix);
                    }else {
                        wrongDirection = true;
                    }
                    break;
                case "left":
                    if (dillingerCol - 1 >= 0) {
                        checkPosition(0, -1, matrix);
                    }else {
                        wrongDirection = true;
                    }
                    break;
            }

            if (wrongDirection) {
                System.out.println("You cannot leave the town, there is police outside!");
            }

            if (isDillingerOnTheJail) {
                System.out.printf("You got caught with %d$, and you are going to jail.%n", stolenMoney);
                break;
            }
        }

        if (!isDillingerOnTheJail) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", stolenMoney);
        }

        printMatrix(matrix);
    }

    private static void checkPosition(int currRowModifier, int currColModifier, char[][] matrix) {

        int currRow = dillingerRow + currRowModifier;
        int currCol = dillingerCol + currColModifier;

        if (matrix[currRow][currCol] == '$') {
            int moneyToSteal = currRow * currCol;
            stolenMoney += moneyToSteal;

            matrix[dillingerRow][dillingerCol] = '+';
            matrix[currRow][currCol] = 'D';
            dillingerRow = currRow;
            dillingerCol = currCol;
            System.out.printf("You successfully stole %d$.%n", moneyToSteal);
        } else if (matrix[currRow][currCol] == 'P') {
            matrix[dillingerRow][dillingerCol] = '+';
            matrix[currRow][currCol] = '#';
            dillingerRow = currRow;
            dillingerCol = currCol;
            isDillingerOnTheJail= true;

        }else {
            matrix[dillingerRow][dillingerCol] = '+';
            matrix[currRow][currCol] = 'D';
            dillingerRow = currRow;
            dillingerCol = currCol;
        }
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
