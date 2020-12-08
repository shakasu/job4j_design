package ru.job4j.design.lsp.parking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class ParkingTest {

    @Test(expected = Exception.class)
    public void whenToMuch() throws Exception {
        Car bigCar = new Truck(3);
        Car anotherBigCar = new Truck(3);
        Parkable parling = new Parking(0, 1, 3);
        parling.letIn(bigCar);
        parling.letIn(anotherBigCar);
    }

    @Test
    public void letInAndReleaseFrom() throws Exception {
        Car car = new Truck(1);
        Car bigCar = new Truck(3);
        Parkable parkable = new Parking(2, 2, 3);
        assertThat(parkable.size(), is (0));
        parkable.letIn(car);
        parkable.letIn(bigCar);
        assertThat(parkable.size(), is (4));
        parkable.releaseFrom(bigCar);
        assertThat(parkable.size(), is (1));
    }

    @Test
    public void emptySpace() throws Exception {
        Car bigCar = new Truck(3);
        Parkable parkable = new Parking(0, 3, 3);
        assertThat(parkable.emptySpace(), is(9));
        parkable.letIn(bigCar);
        assertThat(parkable.emptySpace(), is(6));
    }

    @Test
    public void whenTruckParkingOnCarPlace() throws Exception {
        Car bigCar = new Truck(3);
        Car anotherBigCar = new Truck(3);
        Parkable parkable = new Parking(10, 1, 3);
        assertThat(parkable.emptySpace(), is(13));
        parkable.letIn(bigCar);
        assertThat(parkable.emptySpace(), is(10));
        parkable.letIn(anotherBigCar);
        assertThat(parkable.emptySpace(), is(7));
    }

    @Test
    public void isTruck() {
        Car anotherBigCar = new Truck(3);
        Car car = new Truck(1);
        Parking parkable = new Parking(10, 1, 3);
        assertThat(parkable.isTruck(anotherBigCar), is(true));
        assertThat(parkable.isTruck(car), is(false));
    }
}