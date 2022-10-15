package ThroneConquering_02;

import java.util.Scanner;

public class Main {
    private static int parisRow = 0;
    private static int parisCol = 0;
    private static int parisEnergy = 0;
    private static boolean isHelenFound = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        parisEnergy = Integer.parseInt(scanner.nextLine());

        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String [n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = scanner.nextLine().split("");
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("P")) {
                    parisRow = i;
                    parisCol = j;
                }
            }
        }

        while (parisEnergy > 0) {
            if (isHelenFound) {
                System.out.println(String.format("Paris has successfully abducted Helen! Energy left: %d", parisEnergy));
                matrix[parisRow][parisCol] = "-";
                break;
            }

            String[] input = scanner.nextLine().split("\\s+");

            String direction = input[0];
            int spartansRow = Integer.parseInt(input[1]);
            int spartansCol = Integer.parseInt(input[2]);

            matrix[spartansRow][spartansCol] = "S";

            parisEnergy--;

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
        }
        if (!isHelenFound) {
            matrix[parisRow][parisCol] = "X";
            System.out.println(String.format("Paris died at %d;%d.", parisRow, parisCol));
        }

        printMatrix(matrix);
    }

    private static void checkCoordinates(int rowIndex, int colIndex, String[][] matrix) {

        int currRow = parisRow + rowIndex;
        int currCol = parisCol + colIndex;

        if (matrix[currRow][currCol].equals("S")) {

            parisEnergy -= 2;

        } else if (matrix[currRow][currCol].equals("H")) {

            isHelenFound = true;
        }

        matrix[parisRow][parisCol] = "-";
        parisRow = currRow;
        parisCol = currCol;
        matrix[parisRow][parisCol] = "P";

    }

    private static void printMatrix(String[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {

                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
