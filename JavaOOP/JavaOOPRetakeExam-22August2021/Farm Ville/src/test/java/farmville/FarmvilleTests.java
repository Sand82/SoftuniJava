package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FarmvilleTests {

    private Farm farm;

    private Animal firstAnimal;

    private Animal secondAnimal;

    @Before
    public void setUp() {
        farm = new Farm("Test", 10);

        firstAnimal = new Animal("Dog", 50.0);
        secondAnimal = new Animal("Cat", 40.0);
    }

    @Test
    public void addShouldReturnCorrectAnswer() {

        farm.add(firstAnimal);
        farm.add(secondAnimal);

        Assert.assertEquals(farm.getCount(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenNoFreeSpaceInIt() {

        Farm farmForTwo = new Farm("Test2", 2);

        farmForTwo.add(firstAnimal);
        farmForTwo.add(secondAnimal);

        farmForTwo.add(new Animal("Zebra", 100));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenAnimalExist() {

        Farm farmForTwo = new Farm("Test2", 10);

        farmForTwo.add(firstAnimal);
        farmForTwo.add(secondAnimal);

        farmForTwo.add(new Animal("Cat", 100));
    }

    @Test
    public void removeShouldReturnCorrectAnswer() {

        farm.add(secondAnimal);
        farm.add(firstAnimal);

        Assert.assertTrue(farm.remove("Cat"));
        Assert.assertEquals(farm.getCount(), 1);
    }

    @Test
    public void removeShouldThrowExceptionWhenAnimalIsNull() {

        farm.add(firstAnimal);
        farm.add(secondAnimal);

        Assert.assertFalse(farm.remove("Snake"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowExceptionWhenCapacityIsBelowZero() {

        Farm farm1 = new Farm("test2", -1);
    }

    @Test(expected = NullPointerException.class)
    public void setNamShouldThrowExceptionWhenNameIsNull() {

        Farm farm1 = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void setNamShouldThrowExceptionWhenNameIsWhiteSpace() {

        Farm farm1 = new Farm("   ", 10);
    }

    @Test
    public void getNameShouldReturnCorrectResult() {

        Assert.assertEquals(farm.getName(), "Test");
    }
}
