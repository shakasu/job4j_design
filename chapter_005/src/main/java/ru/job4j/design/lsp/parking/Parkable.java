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
     * Checking that parking contains the car.
     * @param car - car.
     * @return - result of checking.
     */
    boolean contains(Car car);
}
