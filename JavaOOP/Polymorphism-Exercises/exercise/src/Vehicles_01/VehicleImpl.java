package Vehicles_01;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {

    protected double quantity;
    protected double consumption;

    protected DecimalFormat df;

    public VehicleImpl(double quantity, double consumption) {
        this.quantity = quantity;
        this.consumption = consumption;

        df = new DecimalFormat("#.##");
    }

    public double getQuantity() {
        return quantity;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
