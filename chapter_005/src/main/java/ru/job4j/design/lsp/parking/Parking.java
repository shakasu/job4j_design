package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Base implementation of Parkable.
 */
public class Parking implements Parkable {
    private int currentCar;
    private int currentTruck;
    private final List<Car> cars;
    private int space;
    private final int truckCellSize;

    /**
     * Constructor.
     * @param spaceForCar - free space for car.
     * @param spaceForTruck - free space for truck.
     * @param truckCellSize - cell ratio truck to car.
     */
    public Parking(int spaceForCar, int spaceForTruck, int truckCellSize) {
        this.currentCar = 0;
        this.currentTruck = 0;
        this.cars = new ArrayList<>(spaceForCar + spaceForTruck);
        this.space = spaceForCar + spaceForTruck * truckCellSize;
        this.truckCellSize = truckCellSize;

    }

    /**
     * Checking that the car is track.
     * @param car - car.
     * @return - result of checking.
     */
    public boolean isTruck(Car car) {
        return ((double) car.size() / (double) truckCellSize) % 1 == 0;
    }

    @Override
    public void letIn(Car car) {
        if (emptySpace() < car.size()) {
            throw new ArrayIndexOutOfBoundsException("The car is too big.");
        }
        if (isTruck(car)) {
            currentTruck++;
        } else {
            currentCar++;
        }
        cars.add(car);
    }

    @Override
    public void releaseFrom(Car car) {
        if (emptySpace() == space) {
            throw new NoSuchElementException("No cars for release.");
        }
        if (isTruck(car)) {
            currentTruck--;
        } else {
            currentCar--;
        }
        cars.remove(car);
    }

    @Override
    public int emptySpace() {
        return space - currentCar - currentTruck * truckCellSize;
    }

    @Override
    public int size() {
        return space - emptySpace();
    }
}
