import java.util.ArrayDeque;
import java.util.Scanner;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] commands = scanner.nextLine().split(" ");

        int numberOfElements = Integer.parseInt(commands[0]);
        int elementsToPop = Integer.parseInt(commands[1]);
        int elementToSearch = Integer.parseInt(commands[2]);

        String [] elements = scanner.nextLine().split(" ");

        ArrayDeque<String> queue = new ArrayDeque<>();

        for (int i = 0; i < numberOfElements; i++) {
            queue.offer(elements[i]);
        }

        for (int i = 0; i < elementsToPop; i++) {
            queue.poll();
        }

        Boolean isPresetElement = false;
        int smallestInteger = Integer.MAX_VALUE;

        if (queue.isEmpty()){
            System.out.println(0);
        }else {

            int stackSize = queue.size();

            for (int i = 0; i < stackSize; i++) {

                int currentValue = Integer.parseInt(queue.poll());

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
