package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {

    private String name;

    private int capacity;

    private Collection<Fish> fish; // wrong collection name ???

    private Collection<Decoration> decorations;

    public BaseAquarium(String name, int capacity) {

        this.setName(name);
        this.capacity = capacity;

        this.fish = new ArrayList<>();
        this.decorations = new ArrayList<>();
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {

        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public void addFish(Fish fish) {

        if (this.fish.size() >= capacity) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }

        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {

        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {

        this.decorations.add(decoration);
    }

    @Override
    public void feed() {

        this.fish.stream().forEach(Fish::eat);
    }

    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();

        String fishesInfo = fish.size() != 0 ?
                fish.stream().map(Fish::getName).collect(Collectors.joining(" ")) :
                "none";

        int decorationCount = decorations.size();
        int comfortCalculation = calculateComfort();

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Fish: %s", fishesInfo));
        sb.append(System.lineSeparator());
        sb.append(String.format("Decorations: %s", decorationCount));
        sb.append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", comfortCalculation));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public Collection<Fish> getFish() {

        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {

        return this.decorations;
    }
}
