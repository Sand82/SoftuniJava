package p01_Database;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    @Test
    public void successfullyAddedElementInDatabase() throws OperationNotSupportedException {
        Integer[] numbers = {1, 2, 3, 4};
        Database dataBase = new Database(1, 2, 3);
        dataBase.add(4);

        Integer num = 4;

        Assert.assertEquals(dataBase.getElements().length, numbers.length);
        Assert.assertEquals(dataBase.getElements()[dataBase.getElements().length -1], num);
    }
}