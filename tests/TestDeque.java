import java.lang.reflect.InvocationTargetException;
import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;
import seminar1.collections.LinkedDeque;

/**
 * Класс тестирующий интерфейс {@link IDeque<Integer>} в двух реализациях:
 * 1) на массиве {@link CyclicArrayDeque<Integer>}
 * 2) на списке {@link LinkedDeque<Integer>}
 */
@RunWith(value = Parameterized.class)
public class TestDeque {

    @Parameterized.Parameter()
    public Class<?> testClass;

    private IDeque<Integer> deque;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Class<?>> data() {
        return Arrays.asList(
                CyclicArrayDeque.class,
                LinkedDeque.class
        );
    }

    public static int[] getRandomArray(int count) {
        int[] randomArray = new int[count];

        for(int i=0; i<count; i++){
            randomArray[i] = new Random().nextInt(count);
        }
        return randomArray;
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            deque = (IDeque<Integer>) testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01_isEmpty() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(deque.size(), 0);
    }

    @Test
    public void test02_PushFront() {
        deque.pushFront(2);
        for (int item: deque) {
            Assert.assertEquals(item, 2);
        }
    }

    @Test
    public void test03_PushBack() {
        deque.pushBack(2);
        for (int item: deque) {
            Assert.assertEquals(item, 2);
        }
    }

    @Test
    public void test04_PopFrontAfterPushBack() {
        deque.pushBack(2);
        Assert.assertEquals((int)deque.popFront(), 2);
    }

    @Test
    public void test05_PopBackAfterPushBack() {
        deque.pushBack(2);
        Assert.assertEquals((int)deque.popBack(), 2);
    }

    @Test
    public void test06_PopFrontAfterPushFront() {
        deque.pushFront(2);
        Assert.assertEquals((int)deque.popFront(), 2);
    }

    @Test
    public void test07_PopBackAfterPushFront() {
        deque.pushFront(2);
        Assert.assertEquals((int)deque.popBack(), 2);
    }

    @Test
    public void test08_AnyPushFront() {
        int[] randomArray = getRandomArray(10);
        for (int i = 0; i < 10; i++) {
            deque.pushFront(randomArray[i]);
        }
        int i = 9;
        for (int item: deque) {
            Assert.assertEquals(randomArray[i--], item);
        }
    }

    @Test
    public void test09_AnyPushBack() {
        int[] randomArray = getRandomArray(10);
        for (int i = 0; i < 10; i++) {
            deque.pushBack(randomArray[i]);
        }
        int i = 0;
        for (int item: deque) {
            Assert.assertEquals(randomArray[i++], item);
        }
    }

    @Test
    public void test10_AnyPopBack() {
        int[] randomArray = getRandomArray(10);
        for (int i = 0; i < 10; i++) {
            deque.pushBack(randomArray[i]);
        }
        for (int i = 9; i >= 0; i--) {
            Assert.assertEquals(randomArray[i], (int)deque.popBack());
        }
    }

    @Test
    public void test011_AnyPopFront() {
        int[] randomArray = getRandomArray(10);
        for (int i = 0; i < 10; i++) {
            deque.pushBack(randomArray[i]);
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(randomArray[i], (int)deque.popFront());
        }
    }

    @Test
    public void test012_FailPopFront() {
        try {
            deque.popFront();
            Assert.fail();
        }
        catch (NoSuchElementException e) {
        }
    }

    @Test
    public void test013_FailPopBack() {
        try {
            deque.popFront();
            Assert.fail();
        }
        catch (NoSuchElementException e) {
        }
    }

    @Test
    public void test014_Iterator() {
        int[] randomArray = getRandomArray(10);
        for (int i = 0; i < 10; i++) {
            deque.pushBack(randomArray[i]);
        }
        Iterator<Integer> iterator = deque.iterator();
        int i = 0;
        while (iterator.hasNext())
            Assert.assertEquals(randomArray[i++], (int)iterator.next());
    }

}
