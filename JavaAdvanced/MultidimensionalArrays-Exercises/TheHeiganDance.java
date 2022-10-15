import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TheHeiganDance {
    private static String Cloud = "Cloud";
    private static int[][] matrix = new int[15][15];
    private static int playerHealth = 18500;
    private static int heiganHealth = 3000000;
    private static int playerPostionRow = 7;
    private static int playerPostionCol = 7;
    private static String magickName = " ";
    private static String priviousMagic = " ";

    private static int priviousMagicRow = 0;
    private static int priviousMagicCow = 0;

    static Map<Integer, String> direction = new LinkedHashMap<>() {{
        put(0, "-1, 0");
        put(1, "0, -1");
        put(2, "+1, 0");
        put(3, "0, +1");
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int heiganDamage = Integer.parseInt(scanner.nextLine());

        boolean isHealdanDead = false;
        boolean isPlaerDead = false;

        fillMatrix(playerHealth);

        while (true) {

            int playerCurrRow = playerPostionRow;
            int playerCurrCol = playerPostionCol;

            String[] input = scanner.nextLine().split(" ");
            magickName = input[0];
            int magicRow = Integer.parseInt(input[1]);
            int magicCol = Integer.parseInt(input[2]);



            heiganHealth -= heiganDamage;

            if (heiganHealth <= 0) {
                isHealdanDead = true;
                break;
            }

            heiganStrike(magickName, magicRow, magicCol);

            isPlayerPostionHit(playerCurrRow, playerCurrCol);

            if (playerHealth <= 0) {
                isPlaerDead =true;
                break;
            }

            if (priviousMagic.equals(Cloud)) {
                magicRange(priviousMagicRow, priviousMagicCow, 0);
                priviousMagicRow = 0;
                priviousMagicCow = 0;
            }
            if (magickName.equals(Cloud)) {
                priviousMagic = Cloud;
            }
        }

        if (isPlaerDead) {
            System.out.println(String.format("Heigan: %d",heiganHealth));
            System.out.println(String.format("Player: Killed by %s",magickName));
            System.out.println(String.format("Final position: %d, %d",playerPostionRow, playerPostionCol));
        }
    }

    private static void isPlayerPostionHit(int row, int col) { //up, right, down, and left.

        boolean isEscape =false;

        for (int i = 0; i < 4; i++) {
            String cordinates = direction.get(i);
            String[] tokens = cordinates.split(", ");
            int currRow = row + Integer.parseInt(tokens[0]);
            int currCol = col + Integer.parseInt(tokens[1]);
             isEscape= escapeShot(currRow, currCol);

            if (isEscape ) {
                break;
            }
        }
        if (!isEscape) {
            
            if (priviousMagic.equals(Cloud)) {
                playerHealth -= 3500;
            }
            if (magickName.equals(Cloud)) {
                playerHealth -= 3500;
            }else {
                playerHealth -= 6000;
            }
            
        }
    }

    private static boolean escapeShot(int row, int col) {
        if (matrix[row][col] == 0 && cordinatsValidator(row, col)) {
            matrix[row][col] = playerHealth;
            playerPostionRow = row;
            playerPostionCol = col;
            return true;
        }

        return false;
    }

    private static void heiganStrike(String magickName, int magicRow, int magicCol) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {

                if (row == magicRow && col == magicCol) {

                    if (magickName.equals(Cloud)) {
                        matrix[row][col] = 1;
                        priviousMagicRow = row;
                        priviousMagicCow = col;
                        magicRange(magicRow, magicCol, 1);
                    } else {
                        matrix[row][col] = 2;
                    }
                }
            }
        }
    }

    private static void magicRange(int row, int col, int value) {
        setMatrixMagicRange(row - 1, col - 1, value);
        setMatrixMagicRange(row - 1, col, value);
        setMatrixMagicRange(row - 1, col + 1, value);
        setMatrixMagicRange(row, col + 1, value);
        setMatrixMagicRange(row + 1, col + 1, value);
        setMatrixMagicRange(row + 1, col, value);
        setMatrixMagicRange(row + 1, col - 1, value);
        setMatrixMagicRange(row, col - 1, value);
    }

    private static void setMatrixMagicRange(int row, int col, int value) {

        if (cordinatsValidator(row, col)) {
            matrix[row][col] = value;
        }
    }

    private static Boolean cordinatsValidator(int magicRow, int magicCol) {
        return magicRow >= 0 && magicRow < 15 && magicCol >= 0 && magicCol < 15;
    }

    private static void fillMatrix(int playerHealth) {

        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = 0;
            }
        }
        matrix[7][7] = playerHealth;
    }
}
