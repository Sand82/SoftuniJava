package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;

    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable = false;
    private double allPeople = 0;

    public BaseTable(int number, int size, double pricePerPerson) {

        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;

        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    public void setSize(int size) {

        if (size < 0) {

            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {

        if (numberOfPeople <= 0) {

            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {

        return this.number;
    }

    @Override
    public int getSize() {

        return this.size;
    }

    @Override
    public int numberOfPeople() {

        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {

        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {

        return isReservedTable;
    }

    @Override
    public double allPeople() {

        return numberOfPeople * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {

        setNumberOfPeople(numberOfPeople);

        if (numberOfPeople <= size) {

            isReservedTable = true;
        }
    }

    @Override
    public void orderHealthy(HealthyFood food) {

        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {

        this.beverages.add(beverages);
    }

    @Override
    public double bill() {

        double totalBeveragesPrice = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        double totalFoodPrice = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();

        double allPeoplePrice = allPeople();

        return totalBeveragesPrice + totalFoodPrice + allPeoplePrice;
    }

    @Override
    public void clear() {

        beverages.clear();
        healthyFood.clear();

        isReservedTable = false;
        allPeople = 0;
        numberOfPeople = 0;
    }

    @Override
    public String tableInformation() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Table - %d", this.number));
        sb.append(System.lineSeparator());
        sb.append(String.format("Size - %d", this.size));
        sb.append(System.lineSeparator());
        sb.append(String.format("Type - %d", this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("All price - %f.2", pricePerPerson));

        return sb.toString();
    }
}
