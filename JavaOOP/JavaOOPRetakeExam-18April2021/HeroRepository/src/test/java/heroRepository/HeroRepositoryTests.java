package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;

    private Hero firstHero;
    private Hero secondHero;

    @Before
    public void setUp() {

        heroRepository = new HeroRepository();

        firstHero = new Hero("Test1", 90);
        secondHero = new Hero("Test2", 91);
    }

    @Test
    public void createShouldReturnCorrectAnswer() {

        String expect = "Successfully added hero Test1 with level 90";

        Assert.assertEquals(heroRepository.create(firstHero), expect);
    }

    @Test (expected = NullPointerException.class)
    public void createShouldThrowExceptionWhitNullHero() {

        heroRepository.create(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createShouldThrowExceptionWhenHeroExist() {

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);

        heroRepository.create(firstHero);
    }

    @Test
    public void removeShouldReturnCorrectValue(){

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);

        Assert.assertTrue(heroRepository.remove("Test1"));
        Assert.assertEquals(heroRepository.getCount(), 1);
    }

    @Test (expected = NullPointerException.class)
    public void removeShouldThrowExceptionWhenHeroNameISNull(){

        heroRepository.remove(null);
    }

    @Test (expected = NullPointerException.class)
    public void removeShouldThrowExceptionWhenHeroNameISWhiteSpace(){

        heroRepository.remove("   ");
    }

    @Test
    public void getHeroWithHighestLevelShouldReturnCorrectAnswer() {

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);

        Assert.assertEquals(heroRepository.getHeroWithHighestLevel().getLevel(), 91);
        Assert.assertEquals(heroRepository.getHeroWithHighestLevel().getName(), "Test2");
    }

    @Test
    public void getHeroWithHighestLevelShouldReturnNullWhitEmptyCollection() {

        Assert.assertNull(heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getHeroShouldReturnCorrectAnswer() {

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);

        Assert.assertEquals(heroRepository.getHero("Test2").getLevel(), 91);
        Assert.assertEquals(heroRepository.getHero("Test2").getName(), "Test2");
    }

    @Test
    public void getHeroShouldReturnNullWhitEmptyCollection() {

        Assert.assertNull(heroRepository.getHero("Test2"));
    }

    @Test
    public void getHeroesShouldReturnCorrectAnswer() {

        heroRepository.create(firstHero);
        heroRepository.create(secondHero);

        Assert.assertEquals(heroRepository.getHeroes().size(), 2);
    }
}
