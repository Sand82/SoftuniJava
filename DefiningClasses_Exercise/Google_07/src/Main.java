package Google_07.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<People> peoples = new ArrayList<>();

        String [] input = scanner.nextLine().split(" ");

        while(!input[0].equals("End")){

            String className = input[1];

            switch (className){

            }


            input = scanner.nextLine().split(" ");
        }
    }
}