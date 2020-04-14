package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    SimpleSet<Integer> intArr;
    Iterator<Integer> iter;

    @Before
    public void setUp() {
        intArr = new SimpleSet<>(3);
        iter = intArr.iterator();
        intArr.add(1);
        intArr.add(2);
        intArr.add(3);
    }

    @Test
    public void whenAddDublicate() {
        SimpleSet<Integer> intArr1 = new SimpleSet<>(5);
        intArr1.add(1);
        intArr1.add(2);
        intArr1.add(3);
        intArr1.add(1);
        intArr1.add(2);
        intArr1.add(3);
        Iterator<Integer> iter1 = intArr1.iterator();
        assertThat(iter1.next(), is(1));
        assertThat(iter1.next(), is(2));
        assertThat(iter1.next(), is(3));
        assertNull(iter1.next());
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
        SimpleSet<Integer> intArr1 = new SimpleSet<>(0);
        assertThat(intArr1.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorException() {
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        iter.next();
    }

    @Test
    public void addAndGet() {
        assertThat(intArr.get(0), is(1));
        assertThat(intArr.get(1), is(2));
        assertThat(intArr.get(2), is(3));
    }

    @Test
    public void set() {
        intArr.set(0, 666);
        assertThat(intArr.get(0), is(666));
    }

    @Test
    public void remove() {
        intArr.remove(1);
        assertThat(intArr.get(0), is(1));
        assertThat(intArr.get(1), is(3));
        assertNull(intArr.get(2));
    }
}

