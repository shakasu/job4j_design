package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> values;
    
    public SimpleSet(int size) {
        values = new SimpleArray<>(size);
    }

    public boolean contains(T model) {
        boolean rsl = false;
        for (T t : values) {
            if (model.equals(t)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public void add(T model) {
        if (!contains(model)) {
            values.add(model);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    public int size() {
        return values.size();
    }
}
