package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator<Integer> {
    private final int[][] values;
    private int indexOfArrays = 0;
    private int index = 0;

    public JaggedArrayIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean indexNotHasNext = values[indexOfArrays].length != index;
        boolean indexArrayCanNotMove = values.length - 1 != indexOfArrays;
        return  indexArrayCanNotMove || indexNotHasNext;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        boolean indexHasNext = values[indexOfArrays].length > index;
        if (!indexHasNext) {
            index = 0;
            indexOfArrays++;
        }
        return values[indexOfArrays][index++];
    }
}