package VehiclesExtension_02;

public class Car extends VehicleImpl {

    public Car(double quantity, double consumption, int tankCapacity) {
        super(quantity, consumption, tankCapacity);
    }

    @Override
    public void driving(double distance) {
        consumption += 0.9;
        super.driving(distance);
    }
}
