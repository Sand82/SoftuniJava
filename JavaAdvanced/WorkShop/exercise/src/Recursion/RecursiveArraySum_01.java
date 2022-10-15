package Recursion;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RecursiveArraySum_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = sum(arr, arr.length - 1);

        System.out.println(result);
    }

    private static int sum(int[] arr, int index) {

        if (index == 0) {
            return arr[index];
        }

        return arr[index] += sum(arr, index - 1);
    }
}
