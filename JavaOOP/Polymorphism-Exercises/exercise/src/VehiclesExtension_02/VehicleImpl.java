package VehiclesExtension_02;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {

    protected double quantity;
    protected double consumption;
    protected  int tankCapacity;
    protected DecimalFormat df;

    public VehicleImpl(double quantity, double consumption, int tankCapacity) {
        this.quantity = quantity;
        this.consumption = consumption;
        this.tankCapacity = tankCapacity;

        df = new DecimalFormat("#.##");
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public void driving(double distance) {
        if (quantity >= distance * consumption) {
            quantity -= distance * consumption;

            System.out.println(String.format("%s travelled %s km",getClass().getSimpleName(), df.format(distance)));
        }else {
            System.out.println(String.format("%s needs refueling", getClass().getSimpleName()));
        }
    }

    @Override
    public void refueling(double fuel) {

        if (fuel + quantity > tankCapacity) {

            System.out.println("Cannot fit fuel in tank");
        } else if (fuel <= 0) {

            System.out.println("Fuel must be a positive number");
        }else {

            quantity += fuel;
        }
    }
}
