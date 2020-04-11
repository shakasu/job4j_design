package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
                return genericArray.length > index;
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

    private void callArrayIndexOutOfBoundsException(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(T model) {
        callArrayIndexOutOfBoundsException(cursor);
        if (model != null) {
            this.genericArray[cursor++] = model;
        }
    }

    public void set(int index, T model) {
        callArrayIndexOutOfBoundsException(index);
        if (model != null) {
            this.genericArray[index] = model;
        }
    }

    public void remove(int index) {
        callArrayIndexOutOfBoundsException(index);
        Object tmp;
        genericArray[index] = null;
        for (int i = index + 1; i < size; i++) {
            tmp = genericArray[i];
            genericArray[i] = null;
            genericArray[i - 1] = tmp;
            cursor--;
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        callArrayIndexOutOfBoundsException(index);
        return (T) this.genericArray[index];
    }
}
