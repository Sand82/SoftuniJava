package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar {

    private static final double CUBIC_CENTIMETERS = 5000.0;
    private static final int MIN_HORSE_POWER = 400;
    private static final int MAX_HORSE_POWER = 600;
    private int horsePower;

    public MuscleCar(String model, int horsePower) {
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
