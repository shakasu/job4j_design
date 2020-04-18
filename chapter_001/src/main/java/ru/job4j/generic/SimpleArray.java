package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T>  {
    private Object[] genericArray;
    private final int size;
    private int cursor;

    public SimpleArray(int size) {
        this.size = size;
        this.genericArray = new Object[size];
        this.cursor = 0;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return cursor > index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) genericArray[index++];
            }
        };
    }

    public void add(T model) {
        Objects.checkIndex(cursor, size);
        this.genericArray[cursor++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        this.genericArray[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(genericArray, index, genericArray, index - 1, genericArray.length - index + 1);
    }

    public int size() {
        return cursor;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) this.genericArray[index];
    }
}
