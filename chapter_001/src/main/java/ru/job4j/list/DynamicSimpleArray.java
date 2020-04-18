package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DynamicSimpleArray<E> implements Iterable<E> {
    private Object[] container;
    private int size;
    private int modCount;
    private int cursor = 0;

    public DynamicSimpleArray(int size) {
        modCount = 0;
        this.size = size;
        this.container = new Object[size];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor > index;
            }

            @Override
            public E next() {
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
        modCount++;
        container[cursor++] = value;
    }

    public E get(int index) {
        Objects.checkIndex(index, cursor);
        return (E) container[index];
    }

    private void grow() {
        Object[] objects = new Object[2 * size];
        if (size >= 0) {
            System.arraycopy(container, 0, objects, 0, size);
        }
        container = objects;
        this.size = container.length;
    }

    public int size() {
        return cursor;
    }
}
