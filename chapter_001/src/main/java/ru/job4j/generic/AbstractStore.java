package ru.job4j.generic;

import java.util.NoSuchElementException;

public class AbstractStore<E extends Base> implements Store<E> {
    private SimpleArray<Base> bases;

    public AbstractStore(int size) {
        this.bases = new SimpleArray<>(size);
    }

    @Override
    public void add(Base model) {
        bases.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        int index = validIndexOf(id);
        bases.set(index, model);
        return index != -1;
    }

    @Override
    public boolean delete(String id) {
        int index = validIndexOf(id);
        bases.remove(index);
        return index != -1;
    }

    @Override
    public E findById(String id) {
        return (E) bases.get(validIndexOf(id));
    }

    private int validIndexOf(String id) {
        int result = -1;
        for (int i = 0; i < bases.cursor(); i++) {
            if (bases.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        if (result != -1) {
            return result;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}

