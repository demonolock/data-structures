import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import seminar1.iterators.PeekingIncreasingIterator;

/**
 * Класс тестирующий {@link seminar1.iterators.PeekingIncreasingIterator}
 */
public class TestPeekingIncreasingIterator {

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        new PeekingIncreasingIterator(10, 10, 0, 1).next();
    }

    @Test
    public void testNormal() {
        PeekingIncreasingIterator iter = new PeekingIncreasingIterator(5, 10, 1, 15);
        int prev, curr;
        if (iter.hasNext())
            prev = iter.next();
        else {
            Assert.fail();
            return;
        }
        while (iter.hasNext()) {
            curr = iter.next();
            Assert.assertEquals(prev, curr);
            prev = curr;
        }
    }

    @Test
    public void testStartLargeMax() {
        try {
            new PeekingIncreasingIterator(12, 10, 1, 1);
            Assert.fail();
        }
        catch (IllegalArgumentException e) {}
    }

    @Test
    public void testStepLimitLargeMax() {
        try {
            new PeekingIncreasingIterator(10, 10, 1, 0);
            Assert.fail();
        }
        catch (AssertionError e) {}
    }

    @Test
    public void testStartEqMaxLimitEqGrowth() {
        PeekingIncreasingIterator iter = new PeekingIncreasingIterator(10, 10, 1, 1);
        int prev, curr;
        if (iter.hasNext())
            prev = iter.next();
        else {
            Assert.fail();
            return;
        }
        while (iter.hasNext()) {
            curr = iter.next();
            Assert.assertEquals(prev, curr);
            prev = curr;
        }
    }
}
