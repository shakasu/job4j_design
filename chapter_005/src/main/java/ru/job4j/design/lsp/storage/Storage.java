package ru.job4j.design.lsp.storage;

import ru.job4j.design.lsp.model.Food;

import java.util.List;

/**
 * Base storage interface.
 */
public interface Storage {

    /** Add new item in list.
     * @param food - item.
     */
    void add(Food food);

    /**
     * Return list of items.
     */
    List<Food> getAll();


    /**
     * Will return the presence of an item.
     * @param food - item.
     */
    boolean contains(Food food);
}
