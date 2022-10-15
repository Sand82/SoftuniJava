package GenericBox_01;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Box<Integer> box = new Box<>();

        while(n-- > 0){

            Integer item =Integer.parseInt(scanner.nextLine());
            box.addItem(item);
        }

        System.out.println(box.toString());
    }
}