package VehiclesExtension_02;

public class Bus extends VehicleImpl {

    public Bus(double quantity, double consumption, int tankCapacity) {
        super(quantity, consumption, tankCapacity);
    }

    @Override
    public void driving(double distance) {
        consumption += 1.4;
        super.driving(distance);
    }

    public void driveEmpty (double distance) {
        consumption -= 1.4;
        driving(distance);
    }


    @Override
    public void refueling(double fuel) {
        super.refueling(fuel);
    }
}
