package ru.job4j.design.lsp.parking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

public class ParkingTest {
    Car bigCar;
    Car car;
    Parkable parkable;

    @Before
    public void srtUp() {
        car = new Truck(1);
        bigCar = new Truck(3);
        parkable = new Parking(10);
    }

    @Test(expected = Exception.class)
    public void whenToMuch() {
        Parkable parling = new Parking(2);
        parling.letIn(bigCar);
    }

    @Test
    public void letInAndReleaseFrom() {
        assertThat(parkable.size(), is (0));
        parkable.letIn(car);
        parkable.letIn(bigCar);
        assertThat(parkable.size(), is (4));
        parkable.releaseFrom(bigCar);
        assertThat(parkable.size(), is (1));
    }

    @Test
    public void emptySpace() {
        assertThat(parkable.emptySpace(), is(10));
        parkable.letIn(bigCar);
        assertThat(parkable.emptySpace(), is(7));
    }
}