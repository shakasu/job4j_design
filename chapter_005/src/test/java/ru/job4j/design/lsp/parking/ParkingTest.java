package ru.job4j.design.lsp.parking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class ParkingTest {

    @Test
    public void letInAndReleaseFrom() throws Exception {
        Car car = new Truck(1);
        Car bigCar = new Truck(3);
        Parkable parkable = new Parking(2, 2);
        parkable.letIn(car);
        assertThat(parkable.contains(car), is (true));
        parkable.letIn(bigCar);
        assertThat(parkable.contains(bigCar), is (true));
        parkable.releaseFrom(bigCar);
        assertThat(parkable.contains(bigCar), is (false));

    }

    @Test
    public void whenTruckParkingOnCarPlace() throws Exception {
        Car bigCar = new Truck(3);
        Car anotherBigCar = new Truck(3);
        Parkable parkable = new Parking(10, 1);
        parkable.letIn(bigCar);
        assertThat(parkable.contains(bigCar), is (true));
        parkable.letIn(anotherBigCar);
        assertThat(parkable.contains(anotherBigCar), is (true));
    }

    @Test
    public void isTruck() {
        Car anotherBigCar = new Truck(3);
        Car car = new Truck(1);
        Parking parkable = new Parking(10, 1);
        assertThat(parkable.isTruck(anotherBigCar), is(true));
        assertThat(parkable.isTruck(car), is(false));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenToMuch() throws Exception {
        Car car = new Truck(1);
        Car anotherCar = new Truck(1);
        Parkable parling = new Parking(1, 0);
        parling.letIn(car);
        parling.letIn(anotherCar);
    }
}