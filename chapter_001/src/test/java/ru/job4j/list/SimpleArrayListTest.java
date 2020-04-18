package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void ifEmptyContainer() {
        list = new SimpleArrayList<>();
        list.delete();
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDelete() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));
        assertThat(list.delete(), is(2));
        assertThat(list.getSize(), is(1));
        assertThat(list.get(0), is(1));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetInvalidIndex() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
        list.get(123);
    }

}