package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] values;
    private int index = 0;

    public EvenIterator(int[] values) {
        this.values = values;
    }

    private int evenIndex() {
        int result = -1;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return evenIndex() != -1;
    }

    @Override
    public Integer next() {
        if (evenIndex() == -1) {
            throw  new NoSuchElementException();
        }
        int result = values[evenIndex()];
        if (evenIndex() == index + 1) {
            index++;
        }
        index++;
        return result;
    }
}