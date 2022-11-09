package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public abstract class BaseHouse implements House {

    private String name;
    private int capacity;
    private Map<String, Cat> cats;
    private Collection<Toy> toys;

    public BaseHouse(String name, int capacity) {

        this.name = name;
        this.capacity = capacity;

        this.cats = new LinkedHashMap<>();
        this.toys = new ArrayList<>();
    }

    @Override
    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new NullPointerException(HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumSoftness() {

        return this.toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {

        if (cats.size() >= capacity) {

            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
        }

        this.cats.put(cat.getName(), cat);
    }

    @Override
    public void removeCat(Cat cat) {

        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {

        this.toys.add(toy);
    }

    @Override
    public void feeding() {

        this.cats.values().forEach(Cat::eating);
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public Collection<Cat> getCats() {

        return this.cats.values();
    }

    @Override
    public Collection<Toy> getToys() {

        return this.toys;
    }

    @Override
    public String getStatistics() {

        String catInfo = cats.values().size() != 0 ?
                cats.values().stream().map(Cat::getName).collect(Collectors.joining(" "))
                : "none";

        int toysCount = toys.size();
        int sumSoftness = toys.stream().mapToInt(Toy::getSoftness).sum();

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Cats: %s", catInfo));
        sb.append(System.lineSeparator());
        sb.append(String.format("Toys: %d Softness: %d", toysCount, sumSoftness));

        return sb.toString();
    }
}
