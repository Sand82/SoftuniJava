package Recursion;

import java.util.Scanner;

public class RecursiveFactorial_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int result = factorial(n);

        System.out.println(result);
    }

    private static int factorial(int n) {

        if (n == 1) {
            return n;
        }

        return n * factorial(n - 1);
    }
}
