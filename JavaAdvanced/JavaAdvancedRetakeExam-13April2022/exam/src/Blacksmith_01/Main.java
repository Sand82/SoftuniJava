package Blacksmith_01;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> steals = Arrays.stream(scanner.nextLine()
                        .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> carbons = new ArrayDeque<>(); // stack

        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).forEach(carbons::push);

        Map<String, Integer> warHouse = new TreeMap<>();

        int craftCounter = 0;

        while (!steals.isEmpty() && !carbons.isEmpty()) {
            int steal = steals.poll();
            int carbon = carbons.pop();

            int swordsMaterials = steal + carbon;
            String swordName = null;

            switch (swordsMaterials) {
                case 70:
                    swordName = "Gladius";
                    break;
                case 80:
                    swordName = "Shamshir";
                    break;
                case 90:
                    swordName = "Katana";
                    break;
                case 110:
                    swordName = "Sabre";
                    break;
            }

            if (swordName == null) {
                carbon += 5;
                carbons.push(carbon);
            } else {
                craftCounter++;
                warHouse.putIfAbsent(swordName, 0);
                warHouse.put(swordName, warHouse.get(swordName) + 1);
            }
        }

        if (warHouse.size() > 0) {
            System.out.printf("You have forged %d swords.%n", craftCounter);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

        String stealLeft = steals.size() == 0 ?
                "none" :
                steals.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println("Steel left: " + stealLeft);

        String carbonsLeft = carbons.size() == 0 ?
                "none" :
                carbons.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println("Carbon left: " + carbonsLeft);

        for (var sword: warHouse.entrySet()) {
            System.out.printf("%s: %d%n", sword.getKey(), sword.getValue());
        }
    }
}
