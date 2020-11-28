package ru.job4j.design.lsp.storage.storages;

import ru.job4j.design.lsp.storage.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Storage.
 */
public class Trash implements Storage{
    /**
     * List with items.
     */
    private final List<Food> list;

    public Trash() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return food.expirationProgress() >= TRASH;
    }

    @Override
    public List<Food> clear() {
        List<Food> result = list;
        list.clear();
        return result;
    }

    @Override
    public boolean contains(Food food) {
        return list.contains(food);
    }
}

