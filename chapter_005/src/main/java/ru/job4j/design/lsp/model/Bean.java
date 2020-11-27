package ru.job4j.design.lsp.model;

import java.util.Calendar;

/**
 * Base model.
 */
public class Bean extends Food {
    public Bean(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
