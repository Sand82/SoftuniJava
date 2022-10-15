package GenericArrayCreator_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
       String [] strings = ArrayCreator.create(3, "Misho Lubo");

       Integer [] integers = ArrayCreator.create(Integer.class, 3, 10);

        System.out.println(String.join(", ", strings));
    }
}