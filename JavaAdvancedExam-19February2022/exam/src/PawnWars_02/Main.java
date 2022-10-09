package PawnWars_02;

import java.util.Scanner;

public class Main {

    private static int whiteRow = 0;
    private static int whiteCol = 0;
    private static boolean isWhiteWin = false;
    private static int blackRow = 0;
    private static int blackCol = 0;
    private static boolean isBlackWin = false;
    private static String captureMessage = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = new char[8][8];

        for (int row = 0; row < 8; row++) {

            char[] input = scanner.nextLine().toCharArray();
            for (int col = 0; col < 8; col++) {

                matrix[row][col] = input[col];

                if (input[col] == 'w') {
                    whiteRow = row;
                    whiteCol = col;
                }

                if (input[col] == 'b') {
                    blackRow = row;
                    blackCol = col;
                }
            }
        }

        while (true) {

            if (whiteRow - 1 >= 0) {

                matrix[whiteRow][whiteCol] = '-';

                char opponentSymbol = 'b';
                int rowModifier = - 1;
                int colModifier = 0;

                if (whiteCol - 1 >= 0){

                    colModifier = -1;
                    checkForEnemy(matrix, rowModifier + whiteRow, colModifier + whiteCol, opponentSymbol);

                }

                if (whiteCol + 1 < 8) {

                    colModifier = + 1;
                    checkForEnemy(matrix, rowModifier + whiteRow, colModifier + whiteCol, opponentSymbol);

                }

                if(!isWhiteWin){

                    whiteRow += rowModifier;
                }

                matrix[whiteRow][whiteCol] = 'w';

            } else {

                char colSymbol = (char) ('a' + whiteCol);
                String coordinatesString = colSymbol + String.valueOf(8 - whiteRow);

                System.out.printf("Game over! White pawn is promoted to a queen at %s.%n", coordinatesString);
                break;
            }

            if (isWhiteWin ) {
                System.out.println(captureMessage);
                break;
            }

            if (blackRow + 1 < matrix.length) {

                matrix[blackRow][blackCol] = '-';

                char opponentSymbol = 'w';
                int rowModifier = + 1;
                int colModifier = 0;

                if (blackCol - 1 >= 0){

                    colModifier = - 1;
                    checkForEnemy(matrix, rowModifier + blackRow, colModifier + blackCol, opponentSymbol);

                }
                if (blackCol + 1 < 8) {

                    colModifier = + 1;
                    checkForEnemy(matrix, rowModifier + blackRow, colModifier + blackCol, opponentSymbol);

                }

                if(!isBlackWin) {

                    blackRow += rowModifier;
                }

                matrix[blackRow][blackCol] = 'b';

            } else {


                char colSymbol = (char) ('a' + blackCol);
                String coordinatesString = colSymbol + String.valueOf(blackRow + 1 );

                System.out.printf("Game over! Black pawn is promoted to a queen at %s.%n", coordinatesString);
                break;
            }

            if (isBlackWin) {
                System.out.println(captureMessage);
                break;
            }

        }
        //printMatrix(matrix);

    }

    private static void checkForEnemy(char[][] matrix, int row, int col, char opponentSymbol) {
        if (matrix[row][col] == opponentSymbol) {

            if (opponentSymbol == 'w') {

                isBlackWin = true;
                blackRow = row;
                blackCol = col;

                char colSymbol = (char) ('a' + blackCol);
                String coordinatesString = colSymbol + String.valueOf(blackRow + 1);

                captureMessage = String.format("Game over! Black capture on %s.", coordinatesString);
            }else {

                isWhiteWin = true;
                whiteRow = row;
                whiteCol = col;

                char colSymbol = (char) ('a' + whiteCol);
                String coordinatesString = colSymbol + String.valueOf(8 - whiteRow );

                captureMessage = String.format("Game over! White capture on %s.", coordinatesString);
            }
        }
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
