package ru.job4j.design.lsp.storage.model;

import java.util.Calendar;
import java.util.Objects;

/**
 * Base model for extend.
 */
public class Food {
    private String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private double price;
    private final double discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * getters and sеtters.
     * @return - fields.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    /**
     * The method calculates the percentage of the expiration date of item.
     * @return - percentage of the expiration (from 0.0 to 1.0).
     */
    public double expirationProgress() {
        long start = getCreateDate().getTimeInMillis();
        long stop = getExpiryDate().getTimeInMillis();
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
     private double regularCase(long start, long stop, long now) {
        return (now - start) / (double) (stop - start);
    }
}
