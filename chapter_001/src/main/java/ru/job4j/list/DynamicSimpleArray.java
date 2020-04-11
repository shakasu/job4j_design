package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicSimpleArray<E> implements Iterable<E> {

    private Object[] container;

    private int size;

    private int modCount = 0;

    private int cursor = 0;

    public DynamicSimpleArray(int size) {
        this.size = size;
        this.container = new Object[size];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;

            private int index = 0;

            private void checkCME() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkCME();
                return container.length > index;
            }

            @Override
            public E next() {
                checkCME();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[index++];
            }
        };
    }

    public void add(E value) {
        if (cursor == size) {
            grow();
        }
        container[cursor++] = value;
    }

    public E get(int index) {
        return (E) container[index];
    }

    private void grow() {
        modCount++;
        Object[] objects = new Object[2 * size];
        if (size >= 0) {
            System.arraycopy(container, 0, objects, 0, size);
        }
        container = objects;
        this.size = container.length;
    }

    public int size() {
        return size;
    }
}
