package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    ToyRepository toyRepository = new ToyRepository();
    Map<String, House> houses = new LinkedHashMap<>();

    @Override
    public String addHouse(String type, String name) { //"ShortHouse" and "LongHouse".

        if (!type.equals("ShortHouse") && !type.equals("LongHouse")) {

            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        House house = createHouse(type, name);

        houses.put(name, house);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String buyToy(String type) { //"Ball" and "Mouse"

        if (!type.equals("Ball") && !type.equals("Mouse")) {

            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        Toy toy = createToy(type);
        toyRepository.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toy = toyRepository.findFirst(toyType);

        if (toy == null) {

            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        House house = houses.get(houseName);
        house.buyToy(toy);
        toyRepository.removeToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        if (!catType.equals("ShorthairCat") && !catType.equals("LonghairCat")) {

            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        Cat cat = createCat(catType, catName, catBreed, price);
        House house = houses.get(houseName);

        String houseType = house.getClass().getSimpleName();

        boolean isCatCanLiveInLongHouse = catType.equals("LonghairCat") &&
                houseType.equals("LongHouse");

        boolean isCatCanLiveInShortHouse = catType.equals("ShorthairCat") &&
                houseType.equals("ShortHouse");

        if (!isCatCanLiveInShortHouse && !isCatCanLiveInLongHouse) {
            return UNSUITABLE_HOUSE;
        }

        house.addCat(cat);

        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {

        House house = houses.get(houseName);
        house.feeding();

        int fedCats = house.getCats().size();

        return String.format(FEEDING_CAT, fedCats);
    }

    @Override
    public String sumOfAll(String houseName) {

        House house = houses.get(houseName);

        double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();

        double totalHouseCost = toysPrice + catsPrice;

        return String.format(VALUE_HOUSE, houseName, totalHouseCost);
    }

    @Override
    public String getStatistics() {

           StringBuilder sb = new StringBuilder();

        for (House value : houses.values()) {
            sb.append(value.getStatistics());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private Toy createToy(String type) {

        Toy toy = null;

        if (type.equals("Ball")) {

            toy = new Ball();
        } else if (type.equals("Mouse")) {

            toy = new Mouse();
        }

        return toy;
    }

    private House createHouse(String type, String name) {

        House house = null;

        if (type.equals("ShortHouse")) {

            house = new ShortHouse(name);
        } else if (type.equals("LongHouse")) {

            house = new LongHouse(name);
        }

        return house;
    }

    private Cat createCat(String catType, String catName, String catBreed, double price) {

        Cat cat = null;

        if (catType.equals("ShorthairCat")) {

            cat = new ShorthairCat(catName, catBreed, price);
        } else if (catType.equals("LonghairCat")) {

            cat = new LonghairCat(catName, catBreed, price);
        }

        return cat;
    }
}
