package ru.job4j.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> values;
    
    public SimpleSet(int size) {
        values = new SimpleArray<>(size);
    }

    public void add(T model) {
        boolean hasExist = false;
        for (T t : values) {
            if (model.equals(t)) {
                hasExist = true;
                break;
            }
        }
        if (!hasExist) {
            values.add(model);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    public void set(int index, T model) {
        values.set(index, model);
    }

    public void remove(int index) {
        values.remove(index);
    }

    public int size() {
        return values.size();
    }

    public T get(int index) {
        return values.get(index);
    }
}
