import java.util.ArrayList;
import java.util.Scanner;

public class StringMatrixRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rotationString = scanner.nextLine();

        String input = scanner.nextLine();

        ArrayList<String> contentComponents = new ArrayList<>();

        int cols = 0;

        while(!input.equals("END")){

            contentComponents.add(input);

            if (cols < input.length()) {
                cols = input.length();
            }

            input = scanner.nextLine();
        }

        int degreeValue = Integer.parseInt(rotationString.replaceAll("[^0-9]", ""));

        int degrees = degreeValue % 360;

        if (degrees == 90 || degrees == 360) {
            printMatrix90Degrees(contentComponents, cols);
        }else if(degrees == 180){
            printMatrix180Degrees(contentComponents, cols);
        }else if (degrees == 270){
            printMatrix270Degrees(contentComponents, cols);
        }else if(degrees==0){
            printMatrix0Degrees(contentComponents, cols);
        }
    }

    private static void printMatrix0Degrees(ArrayList<String> contentComponents, int cols) {

        String [][] matrix = new String[contentComponents.size()][cols];

        for (int row = 0; row < contentComponents.size(); row++) {

            String [] element = contentComponents.get(row).split("");

            for (int col = 0; col < cols; col++) {

                if (col < element.length) {
                    matrix[row][col] = element[col];
                }else {
                    matrix[row][col] = " ";
                }
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix270Degrees(ArrayList<String> contentComponents, int cols) {
        String [][] matrix = new String[cols][contentComponents.size()];

        int listCounter = contentComponents.size() - 1;

        for (int row = contentComponents.size() - 1; row >= 0 ; row--) {

            String [] element = contentComponents.get(listCounter).split("");
            int elementListCounter = 0;
            listCounter--;
            for (int col = cols - 1; col >= 0; col--) {

                if (cols - element.length > col ) {
                    matrix[col][row] =  " ";
                }else {
                    matrix[col][row] = element[elementListCounter];
                    elementListCounter++;
                }
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix180Degrees(ArrayList<String> contentComponents, int cols) {

        String [][] matrix = new String[contentComponents.size()][cols];
        int listCounter = 0;

        for (int row = contentComponents.size()-1; row >= 0; row--) {

            String [] element = contentComponents.get(listCounter).split("");
            listCounter++;
            int elementListCounter = 0;

            for (int col = cols -1; col >= 0; col--) {

                if (cols - element.length > col ) {

                    matrix[row][col] = " ";
                }else {
                    matrix[row][col] = element[elementListCounter];
                    elementListCounter ++;
                }
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix90Degrees(ArrayList<String> contentComponents, int cols) {

        String [][] matrix = new String[cols][contentComponents.size()];

        int listCounter = 0;

        for (int row = contentComponents.size() -1; row >= 0; row--) {
           String [] element = contentComponents.get(listCounter).split("");


            for (int col = 0; col < cols; col++) {

                if (element.length <= col) {

                    matrix[col][row] = " ";
                }else {
                    matrix[col][row] =element[col];
                }
            }
            listCounter++;
        }

        printMatrix(matrix);
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
