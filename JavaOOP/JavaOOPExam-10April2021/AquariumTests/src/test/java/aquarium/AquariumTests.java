package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {

    private Aquarium aquarium;

    private Fish firstFish;

    private Fish secondFish;

    @Before
    public void setUp() {
        aquarium = new Aquarium("test1", 10);

        firstFish = new Fish("Mish");
        secondFish = new Fish("Lub");
    }

    @Test
    public void setNameShouldReturnCorrectResult() {

        Assert.assertEquals(new Aquarium("Test2", 10).getName(), "Test2");
    }

    @Test (expected = NullPointerException.class)
    public void setNameShouldTrowExceptionWhenNameIsNull() {

        new Aquarium(null, 10);
    }

    @Test (expected = NullPointerException.class)
    public void setNameShouldTrowExceptionWhenNameIsWhiteSpace() {

        new Aquarium("   ", 10);
    }

    @Test
    public void setCapacityShouldReturnCorrectValue() {

        Assert.assertEquals(new Aquarium("Test2", 10).getCapacity(), 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setCapacityShouldTrowExceptionWhenNameIsLessZero() {

        new Aquarium("Test2", -1 );
    }

    @Test
    public void addShouldReturnCorrectResult() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);

        Assert.assertEquals(aquarium.getCount(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowExceptionWhenMistCapacity() {

        Aquarium newAquarium = new Aquarium("test2", 2);

        newAquarium.add(firstFish);
        newAquarium.add(secondFish);

        newAquarium.add(new Fish("Sand"));
    }

    @Test
    public void removeShouldReturnCorrectValue() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);
        aquarium.add(new Fish("Sand"));

        aquarium.remove("Sand");

        Assert.assertEquals(aquarium.getCount(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeShouldThrowExceptionWhenFishIsNull() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);

        aquarium.remove("Pesho");
    }

    @Test
    public void sellFishShouldReturnCorrectValue() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);
        aquarium.add(new Fish("Sand"));

        Assert.assertNotNull(aquarium.sellFish("Sand"));
        Assert.assertNotNull(aquarium.sellFish("Sand").getName(), "Sand");
    }

    @Test
    public void sellFishShouldSetAvailableFishOnFalse() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);
        aquarium.add(new Fish("Sand"));

        aquarium.sellFish("Sand");
        Assert.assertEquals(aquarium.sellFish("Sand").isAvailable(), false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void sellFishThrowExceptionWhenFishIsNull() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);

        aquarium.sellFish("Pesho");
    }

    @Test
    public void reportShouldReturnAllFishesInAquarium() {

        aquarium.add(firstFish);
        aquarium.add(secondFish);

        String expect = "Fish available at test1: Mish, Lub";

        Assert.assertEquals(aquarium.report(), expect);
    }
}

