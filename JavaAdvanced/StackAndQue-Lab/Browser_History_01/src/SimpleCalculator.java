import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] inputs = scanner.nextLine().split(" ");

        ArrayDeque<String> stack = new ArrayDeque<>();

        Collections.addAll(stack, inputs);

        while ( stack.size() > 1){
            int firstNumber = Integer.parseInt(stack.pop());
            String symbol = stack.pop();
            int secondNumber = Integer.parseInt(stack.pop());

            int result = 0;

            if (symbol.equals("+")){
                result = firstNumber + secondNumber;
            }else if (symbol.equals("-")){
                result = firstNumber - secondNumber;
            }
            stack.push(String.valueOf(result));

        }

        System.out.println(stack.peek());
    }
}
