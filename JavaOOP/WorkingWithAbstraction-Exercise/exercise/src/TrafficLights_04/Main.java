package TrafficLights_04;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] trafficLights = scanner.nextLine().split("\\s+");

        int cycle = Integer.parseInt(scanner.nextLine());

        TrafficLightsChanger trafficLightsChanger = new TrafficLightsChanger(trafficLights, cycle);

        System.out.println(trafficLightsChanger.changeLights());
    }
}
