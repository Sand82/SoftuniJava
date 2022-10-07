package Armory_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int officerRow = 0;
    private static int officerCol = 0;

    private static int spendGold = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[n][n];
        List<String> mirrors = new ArrayList<>();

        for (int row = 0; row < n; row++) {

            char[] input = scanner.nextLine().toCharArray();

            for (int col = 0; col < n; col++) {
                matrix[row][col] = input[col];

                if (input[col] == 'A') {
                    officerRow = row;
                    officerCol = col;
                }

                if (input[col] == 'M') {
                    mirrors.add(row + " " + col);
                }
            }
        }

        String command = scanner.nextLine();

        boolean isInArmory = true;

        while (true) {

            switch (command) {
                case "up":
                    if (officerRow - 1 >= 0) {
                        checkCordinates(-1, 0, matrix, mirrors);
                    } else {
                        isInArmory = false;
                    }
                    break;
                case "down":
                    if (officerRow + 1 < n) {
                        checkCordinates(1, 0, matrix, mirrors);
                    } else {
                        isInArmory = false;
                    }
                    break;
                case "right":
                    if (officerCol + 1 < n) {
                        checkCordinates(0, 1, matrix, mirrors);
                    } else {
                        isInArmory = false;
                    }
                    break;
                case "left":
                    if (officerCol - 1 >= 0) {
                        checkCordinates(0, -1, matrix, mirrors);
                    } else {
                        isInArmory = false;
                    }
                    break;
            }

            if (!isInArmory) {
                matrix[officerRow][officerCol] = '-';
                System.out.println("I do not need more swords!");
                break;
            }

            if (spendGold >= 65) {
                break;
            }

            command = scanner.nextLine();

        }

        if (spendGold >= 65) {
            System.out.println("Very nice swords, I will come back for more!");
        }

        System.out.println("The king paid " +spendGold+ " gold coins.");

        printMatrix(matrix);
    }

    private static void checkCordinates(int rowParameter, int colParameters, char[][] matrix, List<String> mirrors) {

        int currRow = officerRow + rowParameter;
        int currCol = officerCol + colParameters;

        if (matrix[currRow][currCol] == 'M') {

            for (int i = 0; i < mirrors.size(); i++) {
                String [] mirrorsCoordinates = mirrors.get(i).split(" ");
                int mirrorRow = Integer.parseInt(mirrorsCoordinates[0]);
                int mirrorsCol = Integer.parseInt(mirrorsCoordinates[1]);

                if (mirrorRow != currRow && mirrorsCol != currCol) {
                    matrix[officerRow][officerCol] = '-';
                    officerRow = mirrorRow;
                    officerCol = mirrorsCol;
                    matrix[mirrorRow][mirrorsCol]= 'A';
                }else {
                    matrix[mirrorRow][mirrorsCol]= '-';
                }
            }


        } else if (Character.isDigit(matrix[currRow][currCol])) {
            spendGold += matrix[currRow][currCol] - 48;

            matrix[officerRow][officerCol] = '-';
            officerRow = currRow;
            officerCol = currCol;
            matrix[officerRow][officerCol] = 'A';
            
        }else if(matrix[currRow][currCol] == '-'){
            matrix[officerRow][officerCol] = '-';
            officerRow = currRow;
            officerCol = currCol;
            matrix[officerRow][officerCol] = 'A';
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
