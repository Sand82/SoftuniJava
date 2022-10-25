package Vehicles_01;

import java.text.DecimalFormat;

public class Truck extends VehicleImpl {

    public Truck(double quantity, double consumption) {
        super(quantity, consumption);
    }

    @Override
    public void driving(double distance) {

        if (quantity >= distance * (1.6 + consumption)) {
            quantity -= distance * (1.6 + consumption);



            System.out.println(String.format("Truck travelled %s km", df.format(distance)));
        }else {
            System.out.println("Truck needs refueling");
        }
    }


    @Override
    public void refueling(double fuel) {
        quantity += fuel * 0.95;
    }
}
