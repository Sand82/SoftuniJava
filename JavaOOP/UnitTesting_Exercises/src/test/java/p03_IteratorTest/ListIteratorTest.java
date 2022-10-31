package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {

    private ListIterator listIterator;
    private String[] elements = {"Mish", "Lub", "Sand", "Mim"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void constructorThrowExceptionWhenArrayArgumentIsANull() throws OperationNotSupportedException {

        String[] elements = null;

        listIterator = new ListIterator(elements);
    }

    @Test
    public void moveShouldReturnCorrectAnswer() throws OperationNotSupportedException {

        for (int i = 0; i < elements.length - 1; i++) {

            Assert.assertTrue(listIterator.move());
        }
        Assert.assertFalse(listIterator.move());
    }

    @Test
    public void hasNextShouldReturnTrue() throws OperationNotSupportedException {

        for (int i = 0; i < elements.length; i++) {

            String name = elements[i];

            Assert.assertTrue(listIterator.hasNext());
        }
    }

    @Test
    public void hasNextShouldReturnFalse() throws OperationNotSupportedException {

        ListIterator currListIterator = new ListIterator();

        Assert.assertFalse(currListIterator.hasNext());
    }

    @Test
    public void printShouldReturnCorrectValue() throws OperationNotSupportedException {

       String value = elements[0];

       Assert.assertEquals(listIterator.print(), value);
    }

    @Test (expected = IllegalStateException.class)
    public void printShouldThrowExceptionWhenArrayIsEmpty() throws OperationNotSupportedException {

        String [] value = {};

        ListIterator newListIterator = new ListIterator(value);

        newListIterator.print();
    }

}