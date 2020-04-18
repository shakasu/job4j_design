package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DynamicSimpleArrayTest {

    DynamicSimpleArray<String> dynArr;

    Iterator<String> iter;

    @Before
    public void setUp() {
        dynArr = new DynamicSimpleArray<>(3);
        dynArr.add("one");
        dynArr.add("two");
        dynArr.add("three");
        iter = dynArr.iterator();
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
        DynamicSimpleArray<Integer> dynamicSimpleArray = new DynamicSimpleArray<>(0);
        assertThat(dynamicSimpleArray.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionNSEE() {
        assertThat(iter.next(), is("one"));
        assertThat(iter.next(), is("two"));
        assertThat(iter.next(), is("three"));
        iter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorExceptionCME() {
        dynArr.add("four");
        iter.next();
    }

    @Test
    public void addAndGet() {
        assertThat(dynArr.get(0), is("one"));
        assertThat(dynArr.get(1), is("two"));
        assertThat(dynArr.get(2), is("three"));
    }

    @Test
    public void grow() {
        assertThat(dynArr.size(), is(3));
        dynArr.add("four");
        dynArr.add("five");
        dynArr.add("six");
        assertThat(dynArr.size(), is(6));
    }
}
