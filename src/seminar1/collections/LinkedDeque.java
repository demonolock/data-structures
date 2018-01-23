package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedDeque () {
        head = tail = null;
        size = 0;
    }

    @Override
    public void pushFront(Item item) {
        Node<Item> curr = new Node<Item>(item, null, null);
        if (isEmpty()) {
            head = tail = curr;
        }
        else {
            head.prev = curr;
            curr.next = head;
            head = curr;
        }
        size++;
    }

    @Override
    public void pushBack(Item item) {
        Node<Item> curr = new Node<Item>(item, null, null);
        if (isEmpty()) {
            head = tail = curr;
        }
        else {
            tail.next = curr;
            curr.prev = tail;
            tail = curr;
        }
        size++;
    }

    @Override
    public Item popFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item elm = head.item;
        if (size == 1) {
            head = tail = null;
        }
        else {
            head = head.next;
        }
        size--;
        return elm;
    }

    @Override
    public Item popBack() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item elm = tail.item;
        if (size == 1) {
            head = tail = null;
        }
        else {
            tail = tail.prev;
        }
        size--;
        return elm;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<Item> {

        Node<Item> curr;

        public LinkedDequeIterator() {
            curr = head;
        }

        public boolean hasNext() {
            return curr.next != null;
        }

        public boolean hasPrev() {
            return curr.prev != null;
        }

        public Item next() {
            Node<Item> ret = curr;
            curr = curr.next;
            return ret.item;
        }

        public Item prev() {
            Node<Item> ret = curr;
            curr = curr.prev;
            return ret.item;
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
