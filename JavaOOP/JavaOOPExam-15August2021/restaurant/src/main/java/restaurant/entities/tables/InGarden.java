package restaurant.entities.tables;

import restaurant.entities.tables.interfaces.Table;

public class InGarden extends BaseTable {

    private static final double PRICE = 4.50;

    public InGarden(int number, int size) {
        super(number, size, PRICE);
    }
}
