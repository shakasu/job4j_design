package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayStringTest {
    SimpleArray<String> strArr;
    Iterator<String> iter;

    @Before
    public void setUp() {
        strArr = new SimpleArray<>(10);
        iter = strArr.iterator();
        strArr.add("one");
        strArr.add("two");
        strArr.add("three");
    }

    @Test
    public void iterator1() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("one"));
        assertThat(iter.next(), is("two"));
        assertThat(iter.next(), is("three"));
    }

    @Test
    public void iterator2() {
        assertThat(iter.next(), is("one"));
        assertThat(iter.next(), is("two"));
        assertThat(iter.next(), is("three"));
    }

    @Test
    public void iterator3() {
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("one"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("two"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("three"));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void iteratorEmptyThenFalse() {
        SimpleArray<Integer> strArr1 = new SimpleArray<>(0);
        assertThat(strArr1.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorException() {
        assertThat(iter.next(), is("one"));
        assertThat(iter.next(), is("two"));
        assertThat(iter.next(), is("three"));
        iter.next();
    }

    @Test
    public void addAndGet() {
        assertThat(strArr.get(0), is("one"));
        assertThat(strArr.get(1), is("two"));
        assertThat(strArr.get(2), is("three"));
    }

    @Test
    public void set() {
        strArr.set(0, "666");
        assertThat(strArr.get(0), is("666"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void remove() {
        strArr.remove(1);
        assertThat(strArr.get(0), is("one"));
        assertThat(strArr.get(1), is("three"));
        assertNull(strArr.get(2));
    }
}
