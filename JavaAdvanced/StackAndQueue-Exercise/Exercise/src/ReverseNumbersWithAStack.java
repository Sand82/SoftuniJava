import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseNumbersWithAStack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] integers = scanner.nextLine().split(" ");
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (String integer : integers) {
            stack.push(Integer.parseInt(integer));
        }

        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            System.out.printf("%s ", stack.pop());
        }
    }
}
