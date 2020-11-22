package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    private MaxMin maxMin;
    private List<Integer> list;
    private Comparator<Integer> comparator;

    @Before
    public void setUp() {
        maxMin = new MaxMin();
        list = List.of(1, 2, 3, 4, 5);
        comparator = Comparator.naturalOrder();
    }

    @Test
    public void testMax() {
        assertThat(
                maxMin.max(list, comparator),
                is(5)
        );
    }

    @Test
    public void testMin() {
        assertThat(
                maxMin.min(list, comparator),
                is(1)
        );
    }
}
