package ru.job4j.design.lsp.parking;

/**
 * Base model of parking.
 * Implementing classes can be use for parking a car.
 */
public interface Parkable {
    /**
     * Lets the car into the parking lot.
     * @param car - car.
     */
    void letIn (Car car);

    /**
     * Release of the car from the parking lot.
     * @param car - car.
     */
    void releaseFrom (Car car);

    public int size();

    public int emptySpace();
}
