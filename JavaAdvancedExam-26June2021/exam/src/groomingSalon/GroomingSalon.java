package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {

    private int capacity;

    private int count;
    private List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {

        if (data.size() < capacity) {
            data.add(pet);
        }
    }

    public boolean remove(String name) {
        Pet pet = getPet(name);

        if (pet == null) {
            return false;
        }

        data.remove(pet);
        return true;
    }

    public Pet getPet(String name) {
        return data.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
    }

    public Pet getPet(String name, String owner) {
        return data.stream()
                .filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The grooming salon has the following clients:");
        sb.append(System.lineSeparator());

        for (var dog : data) {
            sb.append(dog.getName() + " " + dog.getOwner());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
