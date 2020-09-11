package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeSortingTest {

    @Test
    public void testNotEven() {
        int[] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        MergeSorting.mergeSort(array, 0, array.length - 1);
        int[] expected = new int[] {16, 24, 32, 36, 41, 42, 42, 53, 55, 57, 64, 73, 74};
        assertThat(array, is(expected));
    }

    @Test
    public void testEven() {
        int[] array = new int[] {64, 42, 73, 41, 53, 57, 42, 74, 55, 36};
        MergeSorting.mergeSort(array, 0, array.length - 1);
        int[] expected = new int[] {36, 41, 42, 42, 53, 55, 57, 64, 73, 74};
        assertThat(array, is(expected));
    }

    @Test
    public void oneCount() {
        int[] array = new int[] {64};
        MergeSorting.mergeSort(array, 0, array.length - 1);
        int[] expected = new int[] {64};
        assertThat(array, is(expected));
    }

    @Test
    public void twoCount() {
        int[] array = new int[] {64, 2};
        MergeSorting.mergeSort(array, 0, array.length - 1);
        int[] expected = new int[] {2, 64};
        assertThat(array, is(expected));
    }

}
