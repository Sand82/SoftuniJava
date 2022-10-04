package CustomList_07;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");

        CustomList<String> list = new CustomList<>();

        while(!input[0].equals("END")){

            String mainCommand = input[0];

            switch (mainCommand){
                case"Add":
                    list.add(input[1]);
                    break;
                case"Remove":
                   String element = list.remove(Integer.parseInt(input[1]));
                    System.out.println(element);
                    break;
                case"Contains":
                   Boolean isExist =  list.contains(input[1]);
                    System.out.println(isExist);
                    break;
                case"Swap":
                    list.swap(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    break;
                case"Greater":
                    int count = list.countGreaterThan(input[1]);
                    System.out.println(count);
                    break;
                case"Max":
                    String maxResult =list.getMax();
                    System.out.println(maxResult);
                    break;
                case"Min":
                    String minResult = list.getMin();
                    System.out.println(minResult);
                    break;
                case"Sort":
                    Sorter.sort(list);
                    break;
                case"Print":
                    System.out.println(list.toString());
                    break;
                default:
                    throw  new NoSuchElementException("Wrong type of command!");
            }

            input = scanner.nextLine().split(" ");
        }
    }
}
