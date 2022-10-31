package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database dataBase;
    private Integer[] numbers = {12, 2, 33, 40, 5, 15, 12, 2};

    @Before
    public void prepare() throws OperationNotSupportedException {

        dataBase = new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void dataBaseThrowExceptionWhenCreateItWhitMoreThan16Elements() throws OperationNotSupportedException {

        Integer[] newDataBaseInfo = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        dataBase = new Database(newDataBaseInfo);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void dataBaseThrowExceptionWhenCreateItWhitNullElements() throws OperationNotSupportedException {

        Integer[] newDataBaseInfo = {};
        dataBase = new Database(newDataBaseInfo);
    }

    @Test
    public void createSuccessfullyDataBase() throws OperationNotSupportedException {

        dataBase = new Database(numbers);
    }

    @Test
    public void successfullyAddedElementInDatabase() throws OperationNotSupportedException {

        Integer[] dataBaseIntegers = dataBase.getElements();
        dataBase.add(4);

        Assert.assertEquals(dataBase.getElements().length - 1, numbers.length);
        for (int i = 0; i < numbers.length - 1; i++) {
            Assert.assertEquals(dataBaseIntegers[i], numbers[i]);
        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addElementThrowExceptionWhenAddNull() throws OperationNotSupportedException {

        dataBase.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void removeShouldThrowExceptionWhenTryManipulateEmptyArray() throws OperationNotSupportedException {

        for (int i = 0; i < numbers.length + 1; i++) {
            dataBase.remove();
        }

    }

    @Test
    public void successfullyRemoveElement() throws OperationNotSupportedException {

        dataBase.remove();
        Assert.assertEquals(dataBase.getElements().length, numbers.length - 1);
    }

    @Test
    public void getElementShouldReturnCorrectValues() {

        for (int i = 0; i < numbers.length - 1; i++) {
            Assert.assertEquals(dataBase.getElements()[i], numbers[i]);
        }
    }

}