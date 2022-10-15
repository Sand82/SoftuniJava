package Browser_History_01.src;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MathPotato {

    private static ArrayDeque<String> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] children = scanner.nextLine().split(" ");
        int removeChildrens = Integer.parseInt(scanner.nextLine());
        int cycle = 1;

        for (int i = 0; i < children.length; i++) {
            queue.offer(children[i]);
        }

        while (queue.size() != 1) {

            String childToRemove = "";

            childToRemove = FindChildrenName(removeChildrens);

            if (IsCimposite(cycle)) {

                System.out.println("Removed " + childToRemove);
            }else {
                queue.push(childToRemove);
                System.out.println("Prime " + childToRemove);
            }
            cycle ++;
        }
        String winer = queue.poll();
        System.out.println("Last is " + winer);
    }

    private static String FindChildrenName(int moveChildrenCount) {

        for (int i = 1; i < moveChildrenCount; i++) {
            String child = queue.poll();
            queue.offer(child);
        }

        String childToRemove = queue.poll();
        return childToRemove;
    }

    private static boolean IsCimposite(int cycle){

        boolean isComposite = false;
        int count = 0;

        for (int i = 1; i < cycle; i++) {

            if (cycle % i == 0) {
                count ++;
            }
        }

        if (count > 1 || cycle == 1) {
            isComposite = true;
        }

        return isComposite;
    }
}
