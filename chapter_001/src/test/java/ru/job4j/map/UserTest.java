package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void whenEqualsAndHashCodeAreNotRedefined() {
        User firstKey = new User("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        User secondKey = new User("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        Map<User, Object> map = new HashMap<>();
        map.put(firstKey, "first value");
        map.put(secondKey, "second value");
        System.out.println(map);
        assertThat(map.size(), is(2));
    }
}
