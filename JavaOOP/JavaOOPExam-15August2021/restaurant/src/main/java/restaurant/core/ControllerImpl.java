package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;

    private double totalMoney = 0.0;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {

        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {

        HealthyFood food = createFood(type, name, price);
        HealthyFood foodExist = healthFoodRepository.foodByName(name);

        if (foodExist != null) {

            return String.format(FOOD_EXIST, name);
        }

        healthFoodRepository.add(food);

        return String.format(String.format(FOOD_ADDED, name));
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {

        Beverages beverages = createBeverages(type, name, brand, counter);

        Beverages beveragesExist = beverageRepository.beverageByName(name, brand);

        if (beveragesExist != null) {

            return String.format(BEVERAGE_EXIST, name);
        }

        beverageRepository.add(beverages);

        return String.format(BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {

        Table table = createTable(type, tableNumber, capacity);

        Table tableAddedInRestaurant = tableRepository.byNumber(tableNumber);

        if (tableAddedInRestaurant != null) {

            return String.format(TABLE_IS_ALREADY_ADDED, tableNumber);
        }

        tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {

        Table table = tableRepository.getAllEntities().stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);

        if (table == null || table.isReservedTable()) {

            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);
        table.isReservedTable();

        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {

            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);

        if (food == null) {

            return String.format(NONE_EXISTENT_FOOD, healthyFoodName, tableNumber);
        }

        table.orderHealthy(food);

        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {

            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverage = beverageRepository.beverageByName(name, brand);

        if (beverage == null) {

            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverage);

        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, brand);
    }

    @Override
    public String closedBill(int tableNumber) {

        Table table = tableRepository.byNumber(tableNumber);

        double bill = table.bill();
        table.clear();

        totalMoney += bill;

        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String totalMoney() {

        return String.format(TOTAL_MONEY, totalMoney);
    }

    private Food createFood(String type, String name, double price) {

        Food food = null;

        if (type.equals("VeganBiscuits")) {

            food = new VeganBiscuits(name, price);
        } else if (type.equals("Salad")) {

            food = new Salad(name, price);
        }

        return food;
    }

    private Beverages createBeverages(String type, String name, String brand, int counter) {

        Beverages beverages = null;

        if (type.equals("Fresh")) {

            beverages = new Fresh(name, counter, brand);
        } else if (type.equals("Smoothie")) {

            beverages = new Smoothie(name, counter, brand);
        }

        return beverages;
    }

    private Table createTable(String type, int tableNumber, int capacity) {

        Table table = null;

        if (type.equals("Indoors")) {

            table = new Indoors(tableNumber, capacity);
        } else if (type.equals("InGarden")) {

            table = new InGarden(tableNumber, capacity);
        }

        return table;
    }
}
