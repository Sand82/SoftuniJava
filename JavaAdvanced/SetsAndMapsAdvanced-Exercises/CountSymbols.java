import java.util.*;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new TreeMap<>();

        String[] input = scanner.nextLine().split("");

        for (int i = 0; i < input.length; i++) {
            var symbol = input[i];

//            if (!map.containsKey(symbol)) {
//                map.put(symbol, 0);
//            }
//            map.put(symbol, map.get(symbol) + 1);

            map.putIfAbsent(symbol, 0);
            map.put(symbol, map.get(symbol) + 1);
        }

        for (var item : map.entrySet()) {
            System.out.println(String.format("%s: %d time/s", item.getKey(), item.getValue()));
        }
    }
}
