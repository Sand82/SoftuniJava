import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        ArrayDeque<String> steck = new ArrayDeque<>();

        while (!input.equals("Home"))
        {
            String curenturl = " ";
            if(input.equals("back")){
                if(steck.size() > 1){
                    steck.pop();
                    curenturl = steck.peek();
                    System.out.println(curenturl);
                }else {
                    System.out.println("no previous URLs");
                }
            }else {
                steck.push(input);
                curenturl = steck.peek();
                System.out.println(curenturl);
            }
            input = scanner.nextLine();
        }
    }
}
