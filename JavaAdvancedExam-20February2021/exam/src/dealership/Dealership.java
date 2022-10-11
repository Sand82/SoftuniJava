package dealership;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dealership {

//    •	name: String
//•	capacity: int

    private String name;

    private int capacity;

    private int count;

    private List<Car> data;

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (data.size() < capacity) {
            data.add(car);
        }
    }

    public boolean buy(String manufacturer, String model) {
        Car car = getCar(manufacturer, model);

        if (car == null) {
            return false;
        }

        data.remove(car);
        return true;
    }

    public Car getLatestCar() {
        return data.stream()
                .max(Comparator.comparingInt(c -> c.getYear()))
                .stream().findFirst().orElse(null);
    }

    public Car getCar(String manufacturer, String model) {
        return  data.stream()
                .filter(c -> c.getManufacturer().equals(manufacturer)&& c.getModel().equals(model))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(" The cars are in a car dealership %s:%n", name));

        for (var car: data) {
            sb.append(String.format("%s%n",car));
        }

        return sb.toString().trim();
    }
}
