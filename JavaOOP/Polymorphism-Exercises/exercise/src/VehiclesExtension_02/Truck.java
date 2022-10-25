package VehiclesExtension_02;

public class Truck extends VehicleImpl {

    public Truck(double quantity, double consumption, int tankCapacity) {
        super(quantity, consumption, tankCapacity);
    }

    @Override
    public void driving(double distance) {
        consumption += 1.6;
        super.driving(distance);
    }

    @Override
    public void refueling(double fuel) {
        super.refueling(fuel * 0.95) ;
    }
}
