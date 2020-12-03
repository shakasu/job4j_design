package ru.job4j.design.lsp.parking;

/**
 * Car implementation.
 */
public class Truck implements Car {
    private final static String name = "Truck";
    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        return size;
    }
}
