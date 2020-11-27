package ru.job4j.design.lsp.parking;

/**
 * Base model of parking.
 * Implementing classes can be use for parking a car.
 */
public interface Parkable {
    /**
     * Car can take up space.
     * @param car - car.
     */
    void takeSpace (Car car);

    /**
     * Car can free up space.
     * @param car - car.
     */
    void freeSpace (Car car);
}
