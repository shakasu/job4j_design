package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = 0;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        while (values[index] % 2 != 0) {
            index++;
        }
        return values[index++];
    }
}