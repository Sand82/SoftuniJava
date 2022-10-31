package p04_BubbleSortTest;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void shouldSortArray () {

        int [] assertValue = {1, 12, 3, 8, 23, 6, 2};
        int [] expectValue = {1, 2, 3, 6, 8, 12, 23};

        Bubble.sort(assertValue);

        for (int i = 0; i < assertValue.length; i++) {
            Assert.assertEquals(assertValue[i], expectValue[i]);
        }
    }
}