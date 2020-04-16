package ru.job4j.generic;

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
        boolean result = false;
        for (int i = 0; i < bases.size();) {
            if (bases.get(i).getId().equals(id)) {
                result = true;
                bases.set(i, model);
                break;
            }
        }
        return result;    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getId().equals(id)) {
                bases.remove(i);
                result = true;
                break;
            }
        }
        return result;    }

    @Override
    public E findById(String id) {
        Base result = null;
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getId().equals(id)) {
                result = bases.get(i);
                break;
            }
        }
        return (E) result;
    }
}

