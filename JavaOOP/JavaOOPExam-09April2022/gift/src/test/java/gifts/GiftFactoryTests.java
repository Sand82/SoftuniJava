package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GiftFactoryTests {

    private GiftFactory giftFactory;
    private Gift firstGift;
    private Gift secondGift;

    @Before
    public void setUp() {

        giftFactory = new GiftFactory();

        firstGift = new Gift("Test1", 20.00);
        secondGift = new Gift("Test2", 30.00);
    }


    @Test
    public void createGiftShouldReturnCorrectResult() {

        String expect = String.format("Successfully added gift %s with magic %.2f.", "Test1", 20.00);

        Assert.assertEquals(giftFactory.createGift(firstGift), expect);
        Assert.assertEquals(giftFactory.getCount(), 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createGiftShouldThrowExceptionWhenGiftExist() {
        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        giftFactory.createGift(firstGift);
    }

    @Test
    public void removeGiftShouldSuccessfullyRemoveValue() {

        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        Assert.assertTrue(giftFactory.removeGift("Test1"));
        Assert.assertEquals(giftFactory.getCount(), 1);
    }

    @Test (expected = NullPointerException.class)
    public void removeGiftShouldThrowExceptionWhenGiftNameIsNull() {
        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        giftFactory.removeGift(" ");
    }

    @Test
    public void removeGiftShouldReturnFalseWhenGiftNotExist() {

        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        Assert.assertEquals(giftFactory.getCount(), 2);
        Assert.assertFalse(giftFactory.removeGift("Test3"));
    }

    @Test
    public void getPresentWithLeastMagicShouldReturnTest1() {

        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        Gift gift = giftFactory.getPresentWithLeastMagic();

        Assert.assertEquals(gift.getType(), "Test1");
    }

    @Test
    public void getPresentWithLeastMagicShouldReturnNull() {

        Assert.assertNull(giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentShouldReturnCorrectValue() {

        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        Assert.assertEquals(giftFactory.getPresent("Test1").getType(), "Test1");
    }

    @Test
    public void getPresentShouldReturnNullValueWhen() {

        Assert.assertNull(giftFactory.getPresent("Test1"));
    }

    @Test
    public void getPresentsShouldReturnCollection() {
        giftFactory.createGift(firstGift);
        giftFactory.createGift(secondGift);

        List<Gift> listGifts = new ArrayList<>();
        listGifts.add(firstGift);
        listGifts.add(secondGift);

        Assert.assertEquals(giftFactory.getPresents().size(), 2);

    }
}
