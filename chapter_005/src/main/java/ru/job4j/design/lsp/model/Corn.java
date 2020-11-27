package ru.job4j.design.lsp.model;

import java.util.Calendar;

/**
 * Base model.
 */
public class Corn extends Food{
    public Corn(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
