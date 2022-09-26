package Browser_History_01.src;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<String> queue = new ArrayDeque<>();

        while(!input.equals("print")){
            if (!input.equals("cancel")) {
                queue.offer(input);
            }else {
                if (queue.size() > 0) {
                    String removePrint = queue.poll();
                    System.out.println("Canceled " + removePrint);
                }else {
                    System.out.println("Printer is on standby");
                }
            }

            input = scanner.nextLine();
        }
        for (String string :queue ){
            System.out.println(string);
        }
    }
}
