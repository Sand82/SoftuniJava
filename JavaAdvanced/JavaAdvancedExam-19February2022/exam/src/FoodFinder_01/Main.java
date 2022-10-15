package FoodFinder_01;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Character> vowels = new ArrayDeque<>(); // queue

        char[] vowelsInput = scanner.nextLine().toCharArray();

        for (int i = 0; i < vowelsInput.length; i++) {
            vowels.offer(vowelsInput[i]);
        }

        ArrayDeque<Character> consonants = new ArrayDeque<>(); // stack

        char[] consonantsInput = scanner.nextLine().toCharArray();

        for (int i = 0; i < consonantsInput.length; i++) {
            consonants.push(consonantsInput[i]);
        }

        String [] words = new String[] {"pear", "flour", "pork", "olive"};
        String [] wordsCopy = new String[] {"pear", "flour", "pork", "olive"};

        while(!consonants.isEmpty()){

            char vowel = vowels.poll();
            char consonant = consonants.pop();
            vowels.offer(vowel);

            for (int i = 0; i < words.length; i++) {

                char [] currWord = words[i].toCharArray();
                String result = "";

                for (int j = 0; j < currWord.length; j++) {

                    if (currWord[j] != vowel && currWord[j] != consonant) {
                        result += currWord[j];
                    }
                }
                words[i] = result;
            }
        }

        System.out.printf("Words found: %s%n", Arrays.stream(words).filter(w -> w.equals("")).count());

        for (int i = 0; i < wordsCopy.length; i++) {

            if (words[i].equals("")) {
                System.out.println(wordsCopy[i]);
            }
        }
    }
}
