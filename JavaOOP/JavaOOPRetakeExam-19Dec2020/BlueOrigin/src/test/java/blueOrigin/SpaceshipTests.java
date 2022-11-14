package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private Spaceship spaceship;

    private Astronaut firstAstronaut;

    private Astronaut secondAstronaut;

    @Before
    public void setUp(){

        spaceship = new Spaceship("test1", 10);

        firstAstronaut = new Astronaut("Mish", 100);
        secondAstronaut = new Astronaut("Lub", 110);
    }

    @Test
    public void addShouldInsertAstronautInCollection() {

        spaceship.add(firstAstronaut);
        spaceship.add(secondAstronaut);

        Assert.assertEquals(spaceship.getCount(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenCapacitySizeIsSameThanCollectionLength(){

        Spaceship fullSpaceship = new Spaceship("test2", 2);

        fullSpaceship.add(firstAstronaut);
        fullSpaceship.add(secondAstronaut);

        fullSpaceship.add(new Astronaut("Sand", 90));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenAstronautExistInCollection(){

        spaceship.add(firstAstronaut);
        spaceship.add(secondAstronaut);

        spaceship.add(firstAstronaut);
    }

    @Test
    public void removeShouldReturnTrue() {

        spaceship.add(firstAstronaut);
        spaceship.add(secondAstronaut);
        spaceship.add(new Astronaut("Sand", 90));

        Assert.assertTrue(spaceship.remove("Sand"));
        Assert.assertEquals(spaceship.getCount(), 2);
    }

    @Test
    public void removeShouldReturnFalse() {

        spaceship.add(firstAstronaut);
        spaceship.add(secondAstronaut);

        Assert.assertFalse(spaceship.remove("Pesho"));
        Assert.assertEquals(spaceship.getCount(), 2);
    }

    @Test
    public void getNameShouldReturnCorrectSpaceshipName() {

        Assert.assertEquals(spaceship.getName(), "test1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowExceptionWhenValueIsLessThanZero(){

       Spaceship negativeCapacitySpaceship = new Spaceship("Test2", -1);
    }

    @Test (expected = NullPointerException.class)
    public void setNameShouldThrowExceptionWhenValueIsNull(){

        Spaceship nullNameSpaceship = new Spaceship(null, 10);
    }

    @Test (expected = NullPointerException.class)
    public void setNameShouldThrowExceptionWhenValueWhiteSpace(){

        Spaceship nullNameSpaceship = new Spaceship("   ", 10);
    }
}
