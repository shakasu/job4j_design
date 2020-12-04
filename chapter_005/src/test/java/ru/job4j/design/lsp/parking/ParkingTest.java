package ru.job4j.design.lsp.parking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class ParkingTest {

    @Test(expected = Exception.class)
    public void whenToMuch() {
        Car bigCar = new Truck(3);
        Car anotherBigCar = new Truck(3);
        Parkable parling = new Parking(0, 1);
        parling.letIn(bigCar);
        parling.letIn(anotherBigCar);
    }

    @Test
    public void letInAndReleaseFrom() {
        Car car = new Truck(1);
        Car bigCar = new Truck(3);
        Parkable parkable = new Parking(2, 2);
        assertThat(parkable.size(), is (0));
        parkable.letIn(car);
        parkable.letIn(bigCar);
        assertThat(parkable.size(), is (4));
        parkable.releaseFrom(bigCar);
        assertThat(parkable.size(), is (1));
    }

    @Test
    public void emptySpace() {
        Car bigCar = new Truck(3);
        Parkable parkable = new Parking(0, 3);
        assertThat(parkable.emptySpace(), is(9));
        parkable.letIn(bigCar);
        assertThat(parkable.emptySpace(), is(6));
    }

    @Test
    public void whenTruckParkingOnCarPlace() {
        Car bigCar = new Truck(3);
        Car anotherBigCar = new Truck(3);
        Car car = new Truck(1);
        Parkable parkable = new Parking(10, 1);
        parkable.letIn(bigCar);
        parkable.letIn(anotherBigCar);
        assertThat(parkable.emptySpace(), is(7));
    }
}