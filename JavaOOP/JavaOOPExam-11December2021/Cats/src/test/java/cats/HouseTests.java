package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseTests {

    private House house;

    private Cat firstCat;
    private Cat secondCat;

    @Before
    public void setUp() {

        house = new House("test1", 10);

        firstCat = new Cat("Mish");
        secondCat = new Cat("Lub");
    }

    @Test
    public void setNameShouldReturnCorrectAnswer() {
        String assertStr = house.getName();
        String expectStr = "test1";

        Assert.assertEquals(assertStr, expectStr);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowExceptionWhenNameIsNull() {

        House nullHouse = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowExceptionWhenNameIsWhiteSpace() {

        House nullHouse = new House("  ", 10);
    }

    @Test
    public void getCapacityShouldReturnCorrectValue() {

        int assertCapacity = house.getCapacity();
        int expectCapacity = 10;

        Assert.assertEquals(assertCapacity, expectCapacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCapacityShouldThrowExceptionWhenCapacityIsLesThanZero() {

        House nullHouse = new House("test2", -1);
    }

    @Test
    public void getCountShouldReturnCorrectAnswer() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        Assert.assertEquals(house.getCount(), 2);
    }

    @Test
    public void addCatShouldReturnCorrectAnswer() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        List<Cat> cats = new ArrayList<>();
        cats.add(firstCat);
        cats.add(secondCat);

        Assert.assertEquals(house.getCount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCatShouldThrowExceptionWhenNoCapacityLeft() {

        House houseForTwo = new House("test2", 2);

        houseForTwo.addCat(firstCat);
        houseForTwo.addCat(secondCat);
        houseForTwo.addCat(new Cat("Mim"));
    }

    @Test
    public void removeCatShouldReturnCorrectResult() {

        house.addCat(firstCat);
        house.addCat(secondCat);
        house.addCat(new Cat("Sand"));

        house.removeCat("Sand");

        Assert.assertEquals(house.getCount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCatShouldShouldThrowExceptionWhenCatIsNull() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        house.removeCat("Pesho");

        Assert.assertEquals(house.getCount(), 2);
    }

    @Test
    public void catForSellShouldReturnCorrectAnswer() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        house.addCat(new Cat("Sand"));

        Assert.assertEquals(house.catForSale("Sand").getName(), "Sand");
        Assert.assertFalse(house.catForSale("Sand").isHungry());
        Assert.assertEquals(house.getCount(), 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void catForSellShouldShouldThrowExceptionWhenCatIsNull() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        house.catForSale("Pesho");
    }

    @Test
    public void statisticsShouldReturnCorrectValue() {

        house.addCat(firstCat);
        house.addCat(secondCat);

        String assertStr = "The cat Mish, Lub is in the house test1!";

        Assert.assertEquals(house.statistics(), assertStr);
    }
}
