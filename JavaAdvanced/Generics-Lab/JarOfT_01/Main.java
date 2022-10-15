package JarOfT_01;

import JarOfT_01.Jar;

public class Main {
    public static void main(String[] args) {
        Jar<Integer> numbers = new Jar<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

       int num = numbers.remove();
        System.out.println(num);
    }
}