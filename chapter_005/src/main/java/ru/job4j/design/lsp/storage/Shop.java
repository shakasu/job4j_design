package ru.job4j.design.lsp.storage;

import ru.job4j.design.lsp.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Storage.
 */
public class Shop implements Storage {
    private final List<Food> list;

    public Shop() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        list.add(food);
    }

    /**
     * Another "add" method.
     * Insert item with discount.
     * @param food - item.
     */
    public void addDiscount(Food food) {
        food.setPrice(food.getPrice() * food.getDiscount());
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
