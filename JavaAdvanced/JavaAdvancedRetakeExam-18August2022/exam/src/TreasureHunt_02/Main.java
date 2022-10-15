package TreasureHunt_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static int playerRow = 0;
    private static int playerCol = 0;

//    private static int treasureRow = 0;
//    private static int treasureCol = 0;

    private static boolean isTreasureFound = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] coordinate = scanner.nextLine().split(" ");

        int fieldRow = Integer.parseInt(coordinate[0]);
        int fieldCol = Integer.parseInt(coordinate[1]);

        char[][] matrix = new char[fieldRow][fieldCol];

        for (int row = 0; row < fieldRow; row++) {
            matrix[row] = scanner.nextLine().replace(" ", "").toCharArray();
        }

        for (int row = 0; row < fieldRow; row++) {

            for (int col = 0; col < fieldCol; col++) {

                if (matrix[row][col] == 'Y') {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }

        List<String> directions = new ArrayList<>();

        List<String> commands = new ArrayList<>();
        String command = scanner.next();

        while (!command.equals("Finish")) {
            commands.add(command);
            command = scanner.next();
        }

        Boolean isFoundTheTreasury = false;

        for (int i = 0; i < commands.size(); i++) {

            switch (commands.get(i)) {
                case "up":
                    if (playerRow - 1 >= 0 && matrix[playerRow - 1][playerCol] != 'T') {
                        //setCoordinate(-1, 0, matrix);
                        playerRow -= 1;
                        directions.add(commands.get(i));
                    }
                    break;
                case "down":
                    if (playerRow + 1 < fieldRow && matrix[playerRow + 1][playerCol] != 'T') {
                        //setCoordinate(+1, 0, matrix);
                        directions.add(commands.get(i));
                        playerRow += 1;
                    }
                    break;
                case "left":
                    if (playerCol - 1 >= 0 && matrix[playerRow][playerCol - 1] != 'T') {
                        //setCoordinate(0, -1, matrix);
                        directions.add(commands.get(i));
                        playerCol -= 1;
                    }
                    break;
                case "right":
                    if (playerCol + 1 < fieldCol && matrix[playerRow][playerCol + 1] != 'T') {
                        //setCoordinate(0, +1, matrix);
                        directions.add(commands.get(i));
                        playerCol += 1;
                    }
                    break;
            }


            if (matrix[playerRow][playerCol] == 'X') {
                isFoundTheTreasury = true;
                break;
            }
        }

        if (isFoundTheTreasury) {
            System.out.println("I've found the treasure!");
            System.out.print("The right path is " + String.join(", ", directions));
        } else {
            System.out.println("The map is fake!");
        }
    }
}
