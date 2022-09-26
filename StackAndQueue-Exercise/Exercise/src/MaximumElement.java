import java.util.ArrayDeque;
import java.util.Scanner;

public class MaximumElement {

    private static ArrayDeque<String> stack = new ArrayDeque<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < N; i++) {
            String[] tokens = scanner.nextLine().split(" ");
            String commands = tokens[0];

            switch (commands) {
                case "1":
                    String element = tokens[1];
                    AddInStack(element);
                    break;
                case "2":
                    DeleteFromStack();
                    break;
                case "3":
                    int maxElement = Integer.MIN_VALUE;

                    for (int j = 0; j < stack.size(); j++) {
                        int currentElement = Integer.parseInt(stack.pop());

                        if (currentElement > maxElement) {
                            maxElement = currentElement;
                        }
                        stack.offer(String.valueOf(currentElement));
                    }

                    System.out.println(maxElement);

                    break;
            }
        }
    }

    private static void DeleteFromStack() {
        stack.pop();
    }

    private static void AddInStack(String element) {
        stack.push(element);
    }
}
