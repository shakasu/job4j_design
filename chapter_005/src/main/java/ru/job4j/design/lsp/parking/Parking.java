package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Base implementation of Parkable.
 */
public class Parking implements Parkable {
    private final List<Car> cars = new ArrayList<>();
    private final List<Car> trucks = new ArrayList<>();
    private final int spaceForCar;
    private final int spaceForTruck;
    private int actualCars = 0;
    private int actualTrucks = 0;

    /**
     * Constructor.
     * @param spaceForCar - free space for car.
     * @param spaceForTruck - free space for truck.
     */
    public Parking(int spaceForCar, int spaceForTruck) {
        this.spaceForCar = spaceForCar;
        this.spaceForTruck = spaceForTruck;
    }

    /**
     * Checking that the car is track.
     * @param car - car.
     * @return - result of checking.
     */
    public boolean isTruck(Car car) {
        return car.size() != 1;
    }

    @Override
    public void letIn(Car car) {
        if (!parkingAvailable(car)) {
            throw new ArrayIndexOutOfBoundsException("There is not enough space in the parking");
        }
        if (isTruck(car)) {
            trucks.add(car);
            actualCars++;
        } else {
            if (actualTrucks == spaceForTruck) {
                actualCars = actualCars + car.size();
            }
            cars.add(car);
            actualTrucks++;
        }
    }

    @Override
    public void releaseFrom(Car car) {
        if (!contains(car)) {
            throw new NoSuchElementException("No cars for release.");
        }
        if (isTruck(car)) {
            trucks.remove(car);
            actualCars--;
        } else {
            if (actualTrucks == spaceForTruck) {
                actualCars = actualCars - car.size();
            }
            cars.remove(car);
            actualTrucks--;
        }
    }

    private boolean parkingAvailable(Car car) {
        boolean result;
        result = isTruck(car) || actualCars != spaceForCar;
        result = (!isTruck(car)
                || actualTrucks != spaceForTruck
                || car.size() <= (spaceForCar - actualCars)
        ) && result;
        return result;
    }

    public boolean contains(Car car) {
        return cars.contains(car) || trucks.contains(car);
    }
}
