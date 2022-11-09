package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GarageTests {

    private Garage garage;
    private Car firstCar;
    private Car secondCar;

    @Before
    public void setUp() {

        garage = new Garage();

        firstCar = new Car("Opel", 220, 10000.0);
        secondCar = new Car("BMW", 250, 20000.0);
    }

    @Test
    public void getCountShouldReturnCorrectAnswer() {

        garage.addCar(firstCar);
        garage.addCar(secondCar);

        Assert.assertEquals(garage.getCount(), 2);
    }

    @Test
    public void findAllCarsWithMaxSpeedAboveShouldReturnCorrectAnswer() {

        garage.addCar(firstCar);
        garage.addCar(secondCar);

        Assert.assertEquals(garage.findAllCarsWithMaxSpeedAbove(200).size(), 2);
        Assert.assertEquals(garage.findAllCarsWithMaxSpeedAbove(220).size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCarShouldThrowExceptionWhenCarIsNull() {

        garage.addCar(null);
    }

    @Test
    public void getTheMostExpensiveCarShouldReturnCorrectAnswer() {

        garage.addCar(firstCar);
        garage.addCar(secondCar);

        Assert.assertEquals(garage.getTheMostExpensiveCar().getBrand(), "BMW");
        Assert.assertEquals(garage.getTheMostExpensiveCar().getPrice(), 20000.0, 0.0);
    }

    @Test
    public void getTheMostExpensiveCarShouldReturnNullWhenCollectionIsEmpty() {

        Assert.assertNull(garage.getTheMostExpensiveCar());
    }

    @Test
    public void findAllCarsByBrandShouldReShouldReturnCorrectValue() {
        garage.addCar(firstCar);
        garage.addCar(secondCar);
        garage.addCar(new Car("BMW", 300, 40000.0));

        Assert.assertEquals(garage.findAllCarsByBrand("BMW").size(), 2);
    }

    @Test
    public void getCarShouldReturnCorrectValue() {
        garage.addCar(firstCar);
        garage.addCar(secondCar);


        Assert.assertEquals(garage.getCars().size(), 2);
    }
}