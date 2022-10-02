import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Scanner;

public class RecursiveFibonacci {
    private static ArrayDeque<Long> stack = new ArrayDeque<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        stack.push(1l);
        stack.push(1l);

        System.out.println(calculateFibonacci(n));
    }

    private static long calculateFibonacci(int n) {

        while (n-- > 0){

            long firstNum = stack.pop();
            long secondNumber = stack.pop();

            long sumNumber = firstNum + secondNumber;

            stack.push(sumNumber);
            stack.push(secondNumber);
        }

        long result = stack.peek();

        return result;
    }
}
