package Browser_History_01.src;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        if (input == 0){
            System.out.print(0);
        }else{
            while (input != 0){
                int result = input % 2;
                stack.push(result);
                input /= 2;
            }

            for (Integer integer : stack){
                System.out.print(integer);
            }
        }
    }
}
