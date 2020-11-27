package ru.job4j.design.lsp;

import ru.job4j.design.lsp.model.Food;
import ru.job4j.design.lsp.storage.Shop;
import ru.job4j.design.lsp.storage.Trash;
import ru.job4j.design.lsp.storage.Warehouse;

import java.util.Calendar;
import java.util.Map;
import java.util.function.Consumer;

public class ControlQuality {
    /**
     * Storages
     */
    private final Shop shop;
    private final Warehouse warehouse;
    private final Trash trash;
    /**
     * Map - dispatcher, substitute nested "if".
     */
    private final Map<Double, Consumer<Food>> dispatcher;

    /**
     * Constants for mapping.
     */
    private final static double TRASH = 1;
    private final static double DISCOUNT = 0.75;
    private final static double SHOP = 0.25;
    private final static double WAREHOUSE = 0;

    /**
     * Method rounds real numbers in four ranges.
     * per <= 0.25 - Warehouse case.
     * per <= 0.75 and per > 0.25 - regular Shop case.
     * per <= 1 and per > 0.75 - discount Shop case.
     * per >= 1 - Trash case.
     * @param per - expiration percentage.
     * @return - key for dispatch map.
     */
    public double rounding (double per) {
        double result = -1;
        if (per >= TRASH) {
            result = TRASH;
        }
        if (per >= DISCOUNT && per < TRASH) {
            result = DISCOUNT;
        }
        if (per >= SHOP && per < DISCOUNT) {
            result = SHOP;
        }
        if (per <= SHOP) {
            result = WAREHOUSE;
        }
        return result;
    }

    public ControlQuality(Shop shop, Warehouse warehouse, Trash trash) {
        this.shop = shop;
        this.warehouse = warehouse;
        this.trash = trash;
        this.dispatcher = immutableMap();
    }

    /**
     * Main public method.
     * @param food - item.
     */
    public void manage(Food food) {
        dispatcher.get(rounding(calculateExpiry(food))).accept(food);
    }

    /**
     * Initial immutable map.
     * @return - map.
     */
    private Map<Double, Consumer<Food>> immutableMap() {
        return Map.of(
                TRASH, toTrash(),
                DISCOUNT, toShopDiscount(),
                SHOP, toShop(),
                WAREHOUSE, toWarehouse()
        );
    }

    /**
     * Functional variables adding items to the desired storage.
     * @return
     */
    private Consumer<Food> toTrash() {
        return trash::add;
    }

    private Consumer<Food> toWarehouse() {
        return warehouse::add;
    }

    private Consumer<Food> toShop() {
        return shop::add;
    }

    private Consumer<Food> toShopDiscount() {
        return shop::addDiscount;
    }

    /**
     * The method calculates the percentage of the expiration date of item.
     * @param food - item.
     * @return - percentage of the expiration.
     */
    public double calculateExpiry(Food food) {
        long start = food.getCreateDate().getTimeInMillis();
        long stop = food.getExpiryDate().getTimeInMillis();
        long now =  Calendar.getInstance().getTimeInMillis();
        if (start > stop) {
            throw new IllegalArgumentException("ExpiryDate before CreateDate");
        }
        double edgeCaseResults = (now < start)? 0 : 1;
        boolean edgeCase = now < start || now > stop;
        return (edgeCase)? edgeCaseResults : regularCase(start, stop, now);
    }

    /**
     * Secondary method for calculating percentage of the expiration.
     * @param start - start of expiration date.
     * @param stop - end of expiration date.
     * @param now - current date.
     * @return - percentage of the expiration, provided that the current time between start and end.
     */
    public double regularCase(long start, long stop, long now) {
        return (now - start) / (double) (stop - start);
    }
}
