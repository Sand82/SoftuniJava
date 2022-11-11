package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop shop;
    private Goods firstGood;
    private Goods secondGood;

    @Before
    public void setUp(){

        shop = new Shop();

        firstGood = new Goods("Test1", "123");
        secondGood = new Goods("Test2", "234");
    }

    @Test
    public void getShelvesShouldReturnCorrectValue() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", firstGood);
        shop.addGoods("Shelves2", secondGood);

        Assert.assertEquals(shop.getShelves().get("Shelves1").getGoodsCode(), "123");
        Assert.assertEquals(shop.getShelves().get("Shelves2").getGoodsCode(), "234");
    }

    @Test
    public void addGoodsShouldReturnCorrectResult() throws OperationNotSupportedException {

        String expect = "Goods: 123 is placed successfully!";

        Assert.assertEquals(shop.addGoods("Shelves1", firstGood), expect);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addGoodsShouldTrowExceptionWhenKeyNotExist() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);

        shop.addGoods("Shelves100", secondGood);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addGoodsShouldTrowExceptionWhenKeyIsAlreadyTaken() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);

        shop.addGoods("Shelves1", secondGood);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void addGoodsShouldTrowExceptionWhenValueIsAlreadyTaken() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);

        shop.addGoods("Shelves2", firstGood);
    }

    @Test
    public void removeGoodsShouldReturnCorrectValue() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);
        shop.addGoods("Shelves2", secondGood);

        String expect = "Goods: 123 is removed successfully!";

        Assert.assertEquals(shop.removeGoods("Shelves1", firstGood), expect);
        Assert.assertEquals(shop.getShelves().get("Shelves2").getGoodsCode(), "234");
        Assert.assertNull(shop.getShelves().get("Shelves1"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeGoodsShouldTrowExceptionWhenKeyNotExist() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);
        shop.addGoods("Shelves2", secondGood);

        shop.removeGoods("Shelves100", firstGood);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeGoodsShouldTrowExceptionWhenValueIsDifferent() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", firstGood);
        shop.addGoods("Shelves2", secondGood);

        shop.removeGoods("Shelves10", secondGood);
    }
}