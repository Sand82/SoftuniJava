package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {

    private PetStore petStore;
    private Animal cat;
    private Animal dog;

    @Before
    public void setUp() {

        petStore = new PetStore();

        cat = new Animal("Cat", 5, 150.00);
        dog = new Animal("Dog", 40, 250.00);
    }

    @Test
    public void getAnimalsShouldReturnCorrectCount() {

        petStore.addAnimal(cat);
        petStore.addAnimal(dog);

        int expect = 2;
        String expectName = "Cat";

        Assert.assertEquals(petStore.getAnimals().size(), expect);
        Assert.assertEquals(petStore.getAnimals().get(0).getSpecie(), expectName);
    }

    @Test
    public void getCountShouldReturnCorrectCount() {

        petStore.addAnimal(cat);
        int expect = 1;

        Assert.assertEquals(petStore.getCount(), expect);
    }

    @Test
    public void findAllAnimalsWithMaxKilogramsShouldReturnCorrectResult() {

        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(cat);
        animalsList.add(dog);

        petStore.addAnimal(cat);
        petStore.addAnimal(dog);

        Assert.assertEquals(petStore.findAllAnimalsWithMaxKilograms(5).size(), 1);
        Assert.assertEquals(petStore.findAllAnimalsWithMaxKilograms(4).size(), 2);

        for (int i = 0; i < animalsList.size(); i++) {
            Assert.assertEquals(animalsList.get(i).getSpecie(), petStore.getAnimals().get(i).getSpecie());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnimalShouldThrowExceptionWhenAnimalIsNull() {
        Animal animalNullValue = null;

        petStore.addAnimal(animalNullValue);
    }

    @Test
    public void getTheMostExpensiveAnimalShouldReturnCorrectValue() {
        petStore.addAnimal(cat);
        petStore.addAnimal(dog);

        Assert.assertEquals(petStore.getTheMostExpensiveAnimal().getSpecie(), "Dog");
    }

    @Test
    public void getTheMostExpensiveAnimalShouldReturnNull() {

        Assert.assertNull(petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieShouldReturnCorrectResult() {

        Assert.assertEquals(petStore.findAllAnimalBySpecie("Cat").size(), 0);

        petStore.addAnimal(cat);
        petStore.addAnimal(dog);
        petStore.addAnimal(new Animal("Cat", 3, 200.00));

        Assert.assertEquals(petStore.findAllAnimalBySpecie("Cat").size(), 2);
    }
}

