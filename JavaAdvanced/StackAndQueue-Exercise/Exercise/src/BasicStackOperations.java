import java.util.ArrayDeque;
import java.util.Scanner;

public class BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] commands = scanner.nextLine().split(" ");

        int numberOfElements = Integer.parseInt(commands[0]);
        int elementsToPop = Integer.parseInt(commands[1]);
        int elementToSearch = Integer.parseInt(commands[2]);

        String [] elements = scanner.nextLine().split(" ");

        ArrayDeque<String> stack = new ArrayDeque<>();

        for (int i = 0; i < numberOfElements; i++) {
            stack.push(elements[i]);
        }

        for (int i = 0; i < elementsToPop; i++) {
            stack.pop();
        }

        Boolean isPresetElement = false;
        int smallestInteger = Integer.MAX_VALUE;

        if (stack.isEmpty()){
            System.out.println(0);
        }else {

            int stackSize = stack.size();

            for (int i = 0; i < stackSize; i++) {

               int currentValue = Integer.parseInt(stack.pop());

                if (currentValue < smallestInteger) {
                    smallestInteger = currentValue;
                }
                if (currentValue == elementToSearch) {
                    isPresetElement = true;
                }
            }

            if (isPresetElement) {
                System.out.println(isPresetElement);
            }else {
                System.out.println(smallestInteger);
            }
        }
    }
}
