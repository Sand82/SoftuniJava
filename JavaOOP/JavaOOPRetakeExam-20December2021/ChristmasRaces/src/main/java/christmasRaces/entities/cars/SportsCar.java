package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {

    private static final double CUBIC_CENTIMETERS = 3000.0;
    private static final int MIN_HORSE_POWER = 250;
    private static final int MAX_HORSE_POWER = 450;
    private int horsePower;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);

        this.setHorsePower(horsePower);
    }

    private void setHorsePower(int horsePower) {

        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {

            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        this.horsePower = horsePower;
    }
}
