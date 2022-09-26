package Browser_History_01.src;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BrowserHistoryUpgrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayDeque<String> urls = new ArrayDeque<>();
        ArrayDeque<String> forwardUrls = new ArrayDeque<>();
        ArrayDeque<String> backUrls = new ArrayDeque<>();
       int count = 0;

        while(!input.equals("Home")){

            String result = "";
            if (!input.equals("forward") && !input.equals("back")) {

                //forwardUrls.push(input);
                //backUrls.offer(input);
                urls.offer(input);
                forwardUrls.push(input);
                backUrls.offer(input);
                count ++;

            }else {
                if (input.equals("forward")) {
                    if (urls.size() > 0){
                            count ++;
                        for (int i = 0; i < count; i++) {
                            result = urls.pop();
                            forwardUrls.offer(result);
                        }

                        System.out.println(result);
                    }else {
                        System.out.println("no next URLs");
                    }
                }else if (input.equals("back")){
                    if (urls.size() > 0){
                            count --;
                        for (int i = 0; i < count; i++) {

                            result = urls.poll();
                            urls.offer(result);
                        }

                        System.out.println(result);
                    }else {
                        System.out.println("no previous URLs");
                    }
                }

            }
            input = scanner.nextLine();
        }
    }
}
