package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car {

    private String model;
    private int horsePower;
    private double cubicCentimeters;


    public BaseCar(String model, int horsePower, double cubicCentimeters) {

        this.setModel(model);
        this.horsePower = horsePower;
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    public void setModel(String model) {

        if (model == null || model.trim().isEmpty() || model.length() < 4) {

            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, 4));
        }
        this.model = model;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return getCubicCentimeters() / getHorsePower() * laps;
    }
}
