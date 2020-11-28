package ru.job4j.design.lsp.storage.storages;

import ru.job4j.design.lsp.storage.model.Food;

import java.util.List;

/**
 * Base storage interface.
 */
public interface Storage {

    /**
     * Distribution Constants.
     */
    double TRASH = 1;
    double DISCOUNT = 0.75;
    double SHOP = 0.25;
    double WAREHOUSE = 0;

    /** Add new item in list.
     * @param food - item.
     */
    void add(Food food);

    /**
     * Check if food fits this storage.
     * @param food - item.
     * @return - the truth that the item fits.
     */
    boolean accept(Food food);

    /**
     * Clears out the storage and returns what was in it.
     * @return - list with items.
     */
    List<Food> clear();

    /**
     * Will return the presence of an item.
     * @param food - item.
     */
    boolean contains(Food food);
}
