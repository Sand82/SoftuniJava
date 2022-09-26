import java.util.*;

public class CountRealNumbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<Double, Integer> map = new LinkedHashMap<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .forEach(key -> {
                    if (!map.containsKey(key)) {
                        map.put(key, 0);
                    }
                    map.put(key, map.get(key) + 1);
                });
                for (Double key: map.keySet()
             ) {
            System.out.println(String.format("%.1f -> %d", key, map.get(key)));
        }
          //my way to solve the problem
//        double [] input = Arrays.stream(scanner.nextLine().split(" "))
//                .mapToDouble(Double::parseDouble)
//                .toArray();
//
//        Map<Double, Integer> map = new LinkedHashMap<>();

//        for (int i = 0; i < input.length; i++) { // my way to solve the problem
//
//            if (!map.containsKey(input[i])) {
//                map.put(input[i], 0);
//            }
//            map.put(input[i], map.get(input[i])+ 1);
//        }
//
//        for (Double key: map.keySet()
//             ) {
//            System.out.println(String.format("%.1f -> %d", key, map.get(key)));
//        }
    }
}
