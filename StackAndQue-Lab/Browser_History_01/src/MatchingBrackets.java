package Browser_History_01.src;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringToSplit = scanner.nextLine().replaceAll(" ","");
        String [] symbols = stringToSplit.split("");
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < symbols.length; i++) {
            list.add(symbols[i]);
        }

        for (int i = 0; i < list.size(); i++) {

            String reverseBracket = list.get(i);


            if (reverseBracket.equals(')')) {

                for (int j = i; j < 0; j--) {

                }
            }
        }
    }
}
