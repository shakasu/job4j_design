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
    void letIn (Car car) throws Exception;

    /**
     * Release of the car from the parking lot.
     * @param car - car.
     */
    void releaseFrom (Car car);

    /**
     * Actual filling of Parkable.
     * @return - number of places filled.
     */
    int size();

    /**
     *Actual filling of Parkable.
     * @return - number of free places.
     */
    int emptySpace();
}
