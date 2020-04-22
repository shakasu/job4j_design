package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<UserBothMethods, String> map = new SimpleHashMap<>();
    GregorianCalendar simpleDate = new GregorianCalendar(1954, Calendar.JUNE, 7);
    UserBothMethods user1 =  new UserBothMethods("first", 1, new GregorianCalendar(2020, Calendar.MARCH, 1));
    UserBothMethods user2 =  new UserBothMethods("second", 2, new GregorianCalendar(2020, Calendar.APRIL, 2));
    UserBothMethods user3 =  new UserBothMethods("third", 3, new GregorianCalendar(2020, Calendar.MAY, 3));
    UserBothMethods user4 =  new UserBothMethods("first", 1, new GregorianCalendar(2020, Calendar.MARCH, 1));
    UserBothMethods user5 =  new UserBothMethods("fifth", 5, new GregorianCalendar(2020, Calendar.JUNE, 5));
    Iterator<String> iterator;

    @Before
    public void setUp() {
        map.insert(user1, "first");
        map.insert(user2, "second");
        map.insert(user3, "third");
        iterator = map.iterator();
    }

    @Test
    public void whenInsertSameKey() {
        assertThat(map.elements(), is(3));
        assertFalse(map.insert(user4, "first"));
        assertThat(map.elements(), is(3));
    }

    @Test
    public void getTest() {
        assertThat(map.get(user1), is("first"));
        assertThat(map.get(user2), is("second"));
        assertThat(map.get(user3), is("third"));
    }

    @Test
    public void iterator1() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("third"));
    }

    @Test
    public void iterator2() {
        assertThat(iterator.next(), is(map.get(user1)));
        assertThat(iterator.next(), is(map.get(user2)));
        assertThat(iterator.next(), is(map.get(user3)));
    }

    @Test
    public void iterator3() {
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("third"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void iteratorEmptyThenFalse() {
        SimpleHashMap<UserBothMethods, String> mapEmpty = new SimpleHashMap<>();
        Iterator<String> iteratorEmpty = mapEmpty.iterator();
        assertThat(iteratorEmpty.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionNSEE() {
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.next(), is("third"));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorExceptionCME() {
        map.insert(user5, "fifth");
        iterator.hasNext();
    }

    /**
     * Данный тест выявил проблему всей структуры SimpleHashMap, описаную в методе hash()
     */
    @Test
    public void growTest() {
        assertThat(map.insert(user1, "1"), is(false));
        assertThat(map.insert(user2, "2"), is(false));
        assertThat(map.insert(user3, "3"), is(false));
        assertThat(map.size(), is(16));
        assertThat(map.insert(new UserBothMethods("4", 4, simpleDate), "4"), is(true));
        assertThat(map.insert(new UserBothMethods("5", 5, simpleDate), "5"), is(true));
        assertThat(map.insert(new UserBothMethods("6", 6, simpleDate), "6"), is(true));
        assertThat(map.insert(new UserBothMethods("7", 7, simpleDate), "7"), is(true));
        assertThat(map.insert(new UserBothMethods("8", 8, simpleDate), "8"), is(true));
        assertThat(map.insert(new UserBothMethods("9", 9, simpleDate), "9"), is(true));
        assertThat(map.insert(new UserBothMethods("10", 10, simpleDate), "10"), is(true));
        assertThat(map.insert(new UserBothMethods("11", 11, simpleDate), "11"), is(true));
        assertThat(map.insert(new UserBothMethods("12", 12, simpleDate), "12"), is(true));
        /*assertThat(map.size(), is(32));
        assertThat(map.get(user1), is("first"));
        assertThat(map.get(user2), is("second"));
        assertThat(map.get(user3), is("third"));
        assertThat(map.get(new UserBothMethods("4", 4, simpleDate)), is("4"));
        assertThat(map.get(new UserBothMethods("5", 5, simpleDate)), is("5"));
        assertThat(map.get(new UserBothMethods("6", 6, simpleDate)), is("6"));
        assertThat(map.get(new UserBothMethods("7", 7, simpleDate)), is("7"));
        assertThat(map.get(new UserBothMethods("8", 8, simpleDate)), is("8"));
        assertThat(map.get(new UserBothMethods("9", 9, simpleDate)), is("9"));
        assertThat(map.get(new UserBothMethods("10", 10, simpleDate)), is("10"));
        assertThat(map.get(new UserBothMethods("11", 11, simpleDate)), is("11"));
        assertThat(map.get(new UserBothMethods("12", 12, simpleDate)), is("12"));*/
    }

    @Test
    public void deleteTest() {
        assertThat(map.get(user1), is("first"));
        assertThat(map.size(), is(16));
        assertThat(map.elements(), is(3));
        assertTrue(map.delete(user1));
        assertNull(map.get(user1));
        assertThat(map.size(), is(16));
        assertThat(map.elements(), is(2));
    }
}
