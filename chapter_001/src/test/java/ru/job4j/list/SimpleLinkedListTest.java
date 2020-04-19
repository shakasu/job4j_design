package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    SimpleLinkedList<String> simpleLL;
    Iterator<String> iter;

    @Before
    public void setUp() {
        simpleLL = new SimpleLinkedList<>();
        simpleLL.add("three");
        simpleLL.add("two");
        simpleLL.add("one");
        iter = simpleLL.iterator();
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
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();
        assertThat(simpleLinkedList.iterator().hasNext(), is(false));
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
        simpleLL.add("four");
        iter.next();
    }

    @Test
    public void addAndGet() {
        assertThat(simpleLL.get(0), is("one"));
        assertThat(simpleLL.get(1), is("two"));
        assertThat(simpleLL.get(2), is("three"));
    }
}
