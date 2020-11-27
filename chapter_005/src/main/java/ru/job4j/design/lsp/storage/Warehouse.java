package ru.job4j.design.lsp.storage;

import ru.job4j.design.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Storage.
 */
public class Warehouse implements Storage {

    /**
     * List with items.
     */
    private final List<Food> list;

    public Warehouse() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public List<Food> getAll() {
        return list;
    }

    @Override
    public boolean contains(Food food) {
        return list.contains(food);
    }
}
