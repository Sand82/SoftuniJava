package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Car> carRepository;

    private Repository<Driver> driverRepository;

    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {

        Driver driverExist = driverRepository.getByName(driver);

        if (driverExist != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        Driver currentDrive = new DriverImpl(driver);

        driverRepository.add(currentDrive);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        Car carExist = carRepository.getByName(model);

        if (carExist != null) {

            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car = createCarType(type, model, horsePower);
        carRepository.add(car);

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), car.getModel());
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) { //: "MuscleCar" and "SportsCar"

        Car car = carRepository.getByName(carModel);

        if (car == null) {

            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        Driver driver = driverRepository.getByName(driverName);

        if (driver == null) {

            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        driver.addCar(car);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        Driver driver = driverRepository.getByName(driverName);

        if (driver == null) {

            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Race race = raceRepository.getByName(raceName);

        if (race == null) {

            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        race.addDriver(driver);

        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        Race race = raceRepository.getByName(raceName);

        if (race == null) {

            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        List<Driver> participants = race.getDrivers().stream().filter(Driver::getCanParticipate).collect(Collectors.toList());

        if (participants.size() < 3) {

            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        int laps = race.getLaps();

        Collections.sort(participants, (d1, d2) -> (int) ((int) d2.getCar().calculateRacePoints(laps) - (d1.getCar().calculateRacePoints(laps))));

        String result = generalRaceResult(participants, raceName);

        return result;
    }

    private String generalRaceResult(List<Driver> participants, String raceName) {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(DRIVER_FIRST_POSITION, participants.get(0).getName(), raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, participants.get(1).getName(), raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, participants.get(2).getName(), raceName));

        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {

        Race raceExist = raceRepository.getByName(name);

        if (raceExist != null) {

            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }

    private Car createCarType(String type, String model, int horsePower) { //"MuscleCar" and "SportsCar"

        Car car = null;

        if (type.equals("Muscle")) {

            car = new MuscleCar(model, horsePower);
        } else if (type.equals("Sports")) {

            car = new SportsCar(model, horsePower);
        }

        return car;
    }
}
