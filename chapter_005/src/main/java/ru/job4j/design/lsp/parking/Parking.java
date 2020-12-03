package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of Parkable.
 */
public class Parking implements Parkable {
   int space;
   List<Car> cars;


    public Parking(int space) {
        this.space = space;
        cars = new ArrayList<>();
    }

    @Override
    public void letIn(Car car) {
        space = space - car.size();
        cars.add(car);
    }

    @Override
    public void releaseFrom(Car car) {
        space = space + car.size();
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
