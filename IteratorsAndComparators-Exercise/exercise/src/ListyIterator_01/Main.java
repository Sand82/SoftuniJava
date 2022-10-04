package ListyIterator_01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        ListyIterator listy = null;

        while (!input[0].equals("END")) {

            String mainCommand = input[0];

            switch (mainCommand) {
                case "Create":
//                    String [] elements = new String[input.length -1];
//
//                    for (int i = 1; i < input.length; i++) {
//                        elements[i - 1] = input[i];
//                    }
                    if (input.length > 0) {
                        listy = new ListyIterator(Arrays.copyOfRange(input, 1, input.length));
                    } else {
                        listy = new ListyIterator();
                    }
                    break;
                case "Move":
                    Boolean isValidMove = listy.MoveNext();
                    System.out.println(isValidMove);
                    break;
                case "HasNext":
                    Boolean isValidElement = listy.HasNext();
                    System.out.println(isValidElement);
                    break;
                case "Print":
                    try{
                        listy.print();
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
            }

            input = scanner.nextLine().split(" ");
        }
    }
}
