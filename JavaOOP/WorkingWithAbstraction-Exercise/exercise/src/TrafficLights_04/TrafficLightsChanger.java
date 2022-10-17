package TrafficLights_04;

public class TrafficLightsChanger {

    private String [] lights;
    private int cycle;

    public TrafficLightsChanger(String[] lights, int cycle) {
        this.lights = lights;
        this.cycle = cycle;
    }

    public String changeLights () {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cycle; i++) {

            for (int j = 0; j < lights.length; j++) {

                TrafficSignal trafficSignal = TrafficSignal.valueOf(lights[j]);

                TrafficLights currTrafficLights = new TrafficLights(trafficSignal);

                Enum<TrafficSignal> currTrafficSignal = currTrafficLights.changeLights();

                lights[j] = currTrafficSignal.name();

                sb.append(currTrafficSignal.name() + " ");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
