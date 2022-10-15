package aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {

    private String name;
    private int capacity;
    private int size;

    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        fishInPool = new ArrayList<>();
    }

    public void add(Fish fish){
        Fish currFish = findFish(fish.getName());

        if (currFish == null && fishInPool.size() < capacity) {
            fishInPool.add(fish);
        }
    }

    public boolean remove(String name) {
        Fish currFish = findFish(name);

        if (currFish == null) {
            return  false;
        }

        fishInPool.remove(currFish);
        return  true;
    }

    public Fish findFish(String name){
        return fishInPool.stream().
                filter(f -> f.getName().equals(name)).
                findFirst().
                orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Aquarium: %s ^ Size: %d%n",name, size));

        for (var fish: fishInPool) {
            sb.append(String.format("%s%n",fish));
        }
        return  sb.toString().trim();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }
}
