package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {

    private static final int NAME_LENGTH = 5;
    private static final int LAPS_LENGTH = 1;
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);

        this.drivers = new ArrayList<>();
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public int getLaps() {

        return this.laps;
    }

    private void setName(String name) {
        
        if (name == null || name.trim().isEmpty() || name.length() < NAME_LENGTH) {

            throw new IllegalArgumentException(String.format(INVALID_NAME, NAME_LENGTH));
        }
        this.name = name;
    }

    public void setLaps(int laps) {

        if (laps < LAPS_LENGTH) {

            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, LAPS_LENGTH));
        }
        
        this.laps = laps;
    }

    @Override
    public Collection<Driver> getDrivers() {

        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {

        if (driver == null) {

           throw new IllegalArgumentException(DRIVER_INVALID);
        }

        if (!driver.getCanParticipate()) {

            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE, driver.getName()));
        }

        Driver driverExist = this.drivers.stream()
                .filter(d -> d.getName().equals(driver.getName()))
                .findFirst().orElse(null);

        if (driverExist != null) {

            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED, driverExist.getName(), this.name));
        }
        
        drivers.add(driver);
    }
}
