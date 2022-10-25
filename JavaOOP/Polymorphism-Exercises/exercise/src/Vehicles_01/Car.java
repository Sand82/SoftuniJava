package Vehicles_01;

public class Car extends VehicleImpl {

    public Car(double quantity, double consumption) {
        super(quantity, consumption);
    }

    @Override
    public void driving(double distance) {

        if (quantity >= (0.9 + consumption) * distance ) {
            quantity -= (0.9 + consumption) * distance;

            System.out.println(String.format("Car travelled %s km", df.format(distance)));
        }else {
            System.out.println("Car needs refueling");
        }
    }

    @Override
    public void refueling(double fuel) {
        quantity += fuel;
    }
}
