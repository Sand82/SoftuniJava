package Browser_History_01.src;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Scanner;

public class HotPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] children = scanner.nextLine().split(" ");
        int removeChildrens = Integer.parseInt(scanner.nextLine());

        ArrayDeque<String> queue = new ArrayDeque<>();

        for (int i = 0; i < children.length; i++) {
            queue.offer(children[i]);
        }

        while(queue.size() != 1){

            for (int i = 1; i < removeChildrens; i++) {
                String child = queue.poll();
                queue.offer(child);
            }

            String childToRemove = queue.poll();
            System.out.println("Removed "+ childToRemove);
        }

        String winer = queue.poll();
        System.out.println("Last is " + winer);
    }
}
