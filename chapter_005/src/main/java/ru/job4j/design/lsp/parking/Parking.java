package ru.job4j.design.lsp.parking;

/**
 * Base implementation of Parkable.
 */
public class Parking implements Parkable {
    Place place;

    public Parking(Place place) {
        this.place = place;
    }

    @Override
    public void takeSpace(Car car) {
    }

    @Override
    public void freeSpace(Car car) {
    }
}
