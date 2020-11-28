package ru.job4j.design.lsp.storage.storages;

import ru.job4j.design.lsp.storage.model.Food;

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
        if (discounted(food)) {
            food.setPrice(food.getPrice() * food.getDiscount());
        }
        list.add(food);
    }

    @Override
    public boolean accept(Food food) {
        double per = food.expirationProgress();
        return per >= SHOP && per < TRASH;
    }

    private boolean discounted(Food food) {
        double per = food.expirationProgress();
        return per > DISCOUNT;
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
