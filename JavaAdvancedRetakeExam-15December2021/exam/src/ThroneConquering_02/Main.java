package ThroneConquering_02;

import java.util.Scanner;

public class Main {

    private static int parisRow = 0;
    private static int parisCol = 0;
    private static int parisEnergy = 0;
    private static boolean isFoundHelen = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        parisEnergy = Integer.parseInt(scanner.nextLine());

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];

        for (int row = 0; row < n; row++) {

            char[] charInput = scanner.nextLine().toCharArray();

            for (int col = 0; col < n; col++) {

                matrix[row][col] = charInput[col];

                if (charInput[col] == 'P') {
                    parisRow = row;
                    parisCol = col;
                }
            }
        }

        String[] input = scanner.nextLine().split("\\s+");

        while (parisEnergy > 0 || !isFoundHelen) {

            String direction = input[0];
            int spartansRow = Integer.parseInt(input[1]);
            int spartansCol = Integer.parseInt(input[2]);

            matrix[spartansRow][spartansCol] = 'S';

            switch (direction) {
                case "up":

                    if (parisRow - 1 >= 0) {
                        checkCoordinates(-1, 0, matrix);
                    }
                    break;
                case "down":

                    if (parisRow + 1 < n) {
                        checkCoordinates(1, 0, matrix);
                    }
                    break;
                case "left":

                    if (parisCol - 1 >= 0) {
                        checkCoordinates(0, -1, matrix);
                    }
                    break;
                case "right":

                    if (parisCol + 1 < n) {
                        checkCoordinates(0, 1, matrix);
                    }
                    break;
            }

            parisEnergy--;

            if (isFoundHelen) {

                System.out.println(String.format("Paris has successfully abducted Helen! Energy left: %d", parisEnergy));
                break;
            }

            if (parisEnergy <= 0) {

                matrix[parisRow][parisCol] = 'X';
                System.out.println(String.format("Paris died at %d;%d.", parisRow, parisCol));
                break;
            }

            input = scanner.nextLine().split("\\s+");
        }

        printMatrix(matrix);
    }

    private static void checkCoordinates(int rowIndex, int colIndex, char[][] matrix) {

        int currRow = parisRow + rowIndex;
        int currCol = parisCol + colIndex;

        if (matrix[currRow][currCol] == 'S') {

            parisEnergy -= 2;
            matrix[currRow][currCol] = 'P';

        } else if (matrix[currRow][currCol] == 'H') {

            isFoundHelen = true;
            matrix[currRow][currCol] = '-';

        } else {
            matrix[currRow][currCol] = 'P';
        }

        matrix[parisRow][parisCol] = '-';
        parisRow = currRow;
        parisCol = currCol;
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
