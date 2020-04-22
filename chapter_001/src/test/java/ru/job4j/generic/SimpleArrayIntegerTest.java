package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayIntegerTest {
    SimpleArray<Integer> intArr;
    Iterator<Integer> iter;

    @Before
    public void setUp() {
        intArr = new SimpleArray<>(3);
        iter = intArr.iterator();
        intArr.add(1);
        intArr.add(2);
        intArr.add(3);
    }

    @Test
    public void sizeTest() {
        assertThat(intArr.cursor(), is(3));
    }

    @Test
    public void iterator1() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
    }

    @Test
    public void iterator2() {
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
    }

    @Test
    public void iterator3() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void iteratorEmptyThenFalse() {
        SimpleArray<Integer> intArr1 = new SimpleArray<>(0);
        assertThat(intArr1.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorException() {
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        iter.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAndGet() {
        assertThat(intArr.get(0), is(1));
        assertThat(intArr.get(1), is(2));
        assertThat(intArr.get(2), is(3));
        intArr.get(45);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set() {
        intArr.set(0, 666);
        assertThat(intArr.get(0), is(666));
        intArr.set(555, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove() {
        intArr.remove(1);
        assertThat(intArr.get(0), is(1));
        assertThat(intArr.get(1), is(3));
        assertNull(intArr.get(2));
        intArr.remove(10);
    }
}

