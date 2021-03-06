package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            public Iterator<Integer> actual = (it.hasNext()) ? it.next() : null;

            @Override
            public boolean hasNext() {
                while (it.hasNext() && !actual.hasNext()) {
                    actual = it.next();
                }
                boolean rsl = false;
                if (actual != null) {
                    rsl = actual.hasNext();
                }
                return rsl;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int rsl = -1;
                if (actual != null) {
                    rsl = actual.next();
                }
                return rsl;
            }
        };
    }
}