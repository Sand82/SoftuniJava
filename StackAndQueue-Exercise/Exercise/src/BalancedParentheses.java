import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split(""); // {[()]}

        int [] array = Arrays.stream(scanner.nextLine().split( " ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> queue = new ArrayDeque<>();

        Boolean isBalanced = true;

        for (String symbol : input) {
            stack.push(symbol);
        }

        for (int i = 0; i < stack.size(); i++) {
            String element = stack.pop();

            if (element.equals("(") || element.equals("{") || element.equals("[")) {
                break;
            }else {
                queue.push(element);
            }
        }

        if (queue.size() != stack.size()) {
            isBalanced = false;
        }else {
            for (int i = 0; i < stack.size(); i++) {
                String queueElement = queue.pop();
                String stackElement = stack.pop();

                if (queueElement.equals(")") && stackElement.equals("(") ||
                        queueElement.equals("}") && stackElement.equals("{") ||
                        queueElement.equals("]") && stackElement.equals("[")) {

                }else {
                    isBalanced = false;
                    break;
                }
            }
        }

        if (isBalanced) {
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}
