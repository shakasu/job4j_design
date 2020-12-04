package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of Parkable.
 */
public class Parking implements Parkable {
   int unitCar;
   int unitTruck;
   List<Car> cars;
   int space;


    public Parking(int unitCar, int unitTruck) {
        this.unitCar = unitCar;
        this.unitTruck = unitTruck;
        cars = new ArrayList<>();
    }

    @Override
    public void letIn(Car car) {
        unitCar = unitCar - car.size();
        cars.add(car);
    }

    @Override
    public void releaseFrom(Car car) {
        unitCar = unitCar + car.size();
        cars.remove(car);
    }

    @Override
    public int emptySpace() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }
}
