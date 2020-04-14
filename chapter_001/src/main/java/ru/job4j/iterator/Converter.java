package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            public Iterator<Integer> actual = it.next();

            @Override
            public boolean hasNext() {
                while (it.hasNext() && !actual.hasNext()) {
                    actual = it.next();
                }
                return actual.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return actual.next();
            }
        };
    }
}