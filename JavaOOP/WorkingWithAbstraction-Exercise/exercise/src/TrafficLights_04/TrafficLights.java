package TrafficLights_04;

public class TrafficLights {

    private TrafficSignal trafficSignal;

    public TrafficLights(TrafficSignal trafficSignal) {
        this.trafficSignal = trafficSignal;
    }

   public Enum<TrafficSignal> changeLights(){

        switch (trafficSignal.name()){

            case "RED":
                return  TrafficSignal.GREEN;
            case"GREEN":
                return  TrafficSignal.YELLOW;
            case "YELLOW":
                return TrafficSignal.RED;
            default:
                throw  new IllegalStateException("Invalid command " + trafficSignal.name());
        }
    }
}
