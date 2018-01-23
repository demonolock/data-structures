package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;
    private int head;
    private int tail;
    private int size;

    public CyclicArrayDeque() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        head = tail = 0;
        size = 0;
    }

    @Override
    public void pushFront(Item item) {
        if (isEmpty()) {
            elementData[head] = item;
            size++;
            return;
        }
        if (size == elementData.length)
            grow();
        elementData[(--head + elementData.length) % elementData.length] = item;
        size++;
    }

    @Override
    public void pushBack(Item item) {
        if (isEmpty()) {
            elementData[head] = item;
            size++;
            return;
        }
        if (size == elementData.length)
            grow();
        elementData[(++tail + elementData.length) % elementData.length] = item;
        size++;
    }

    @Override
    public Item popFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = elementData[(head++ + elementData.length) % elementData.length];
        size--;
        if (size * 4 < elementData.length && size != 0)
            shrink();
        return item;
    }

    @Override
    public Item popBack() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = elementData[(tail-- + elementData.length) % elementData.length];
        size--;
        if (size * 4 < elementData.length  && size != 0)
            shrink();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        changeCapacity((int)(elementData.length * 1.5D));
    }

    private void shrink() {
        changeCapacity((int)(elementData.length / 2D));
    }

    private void changeCapacity(int newCapacity) {
        Item[] newElementData = (Item[]) new Object[newCapacity];
        if ((head + elementData.length) % elementData.length <= (tail + elementData.length) % elementData.length) {
            for (int i = head, j = 0; i <= tail; i++, j++)
                newElementData[j] = elementData[i];
        } else {
            int j = 0;
            for (int i = head; i < elementData.length; i++)
                newElementData[j++] = elementData[i];
            for (int i = 0; i <= tail; i++)
                newElementData[j++] = elementData[i];
        }
        head = 0;
        tail = head + size - 1;
        elementData = Arrays.copyOf(newElementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclicArrayDequeIterator();
    }

    private class CyclicArrayDequeIterator implements Iterator<Item> {

        private int currentPosition = head;

        @Override
        public boolean hasNext() {
            return currentPosition != tail;
        }

        @Override
        public Item next() {
            return elementData[(currentPosition++ + elementData.length) % elementData.length];
        }

    }
}
