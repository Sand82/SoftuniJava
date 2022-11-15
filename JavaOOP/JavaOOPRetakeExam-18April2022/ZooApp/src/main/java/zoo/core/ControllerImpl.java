package zoo.core;

import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static zoo.common.ConstantMessages.*;
import static zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {

        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {

        if (!areaType.equals("WaterArea") && !areaType.equals("LandArea")) {

            throw new NullPointerException(INVALID_AREA_TYPE);
        }

        Area area = null;

        if (areaType.equals("WaterArea")) {

            area = new WaterArea(areaName);
        } else if (areaType.equals("LandArea")) {

            area = new LandArea(areaName);
        }

        areas.add(area);
        String result = String.format(SUCCESSFULLY_ADDED_AREA_TYPE, area.getClass().getSimpleName());

        return result;
    }

    @Override
    public String buyFood(String foodType) {

        if (!foodType.equals("Vegetable") && !foodType.equals("Meat")) {

            throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }

        Food food = null;

        if (foodType.equals("Vegetable")) {
            food = new Vegetable();
        } else if (foodType.equals("Meat")) {
            food = new Meat();
        }

        foodRepository.add(food);
        String result = String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);

        return result;
    }

    @Override
    public String foodForArea(String areaName, String foodType) {

        Food food = foodRepository.findByType(foodType);

        Area area = getArea(areaName);

        if (food == null) {

            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        area.addFood(food);
        foodRepository.remove(food);

        String result = String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);

        return result;
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

        if (!animalType.equals("AquaticAnimal") && !animalType.equals("TerrestrialAnimal")) {

            throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }

        Area area = getArea(areaName);
        Animal animal = null;

        if (animalType.equals("AquaticAnimal")) {

            animal = new AquaticAnimal(animalName, kind, price);
        } else if (animalType.equals("TerrestrialAnimal")) {

            animal = new TerrestrialAnimal(animalName, kind, price);
        }

        boolean isWaterAnimalAndArea = animalType.equals("AquaticAnimal") && area.getClass().getSimpleName().equals("WaterArea");
        boolean isLandAnimalAndArea = animalType.equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("LandArea");

        if (!isLandAnimalAndArea && !isWaterAnimalAndArea) {

            return String.format(AREA_NOT_SUITABLE);
        }

        area.addAnimal(animal);
        String result = String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animal.getClass().getSimpleName(), areaName);

        return result;
    }

    @Override
    public String feedAnimal(String areaName) {

        Area area = getArea(areaName);
        area.feed();

        int feedAnimals = area.getAnimals().size();

        return String.format(ANIMALS_FED, feedAnimals);
    }

    @Override
    public String calculateKg(String areaName) {

        Area area = getArea(areaName);

        double kilogramsNumber = 0.0;

        for (Animal animal : area.getAnimals()) {
            kilogramsNumber += animal.getKg();
        }

        String result = String.format(KILOGRAMS_AREA, areaName, kilogramsNumber);
        return result;
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        for (Area area : areas) {
            sb.append(area.getInfo());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private Area getArea(String areaName) {
        return areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
    }
}
