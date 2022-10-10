package OS_Planing_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tasks = new ArrayDeque<>(); // stack

       Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).forEach(tasks::push);

        ArrayDeque<Integer> threads = Arrays.stream(scanner.nextLine() // queue
                        .split(" ")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));



        int targetValue = Integer.parseInt(scanner.nextLine());

        int killerThread = 0;

        while(true){

            if (tasks.peek().equals(targetValue)) {
                killerThread = threads.peek();
                break;
            }

            int task = tasks.pop();;
            int thread = threads.poll();

            if (thread < task) {
                tasks.push(task);
            }
        }

        System.out.printf("Thread with value %d killed task %d%n", killerThread, targetValue);

        for (var value: threads) {
            System.out.print(value + " ");
        }
    }
}
