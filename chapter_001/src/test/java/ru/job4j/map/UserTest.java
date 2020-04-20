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

    @Test
    public void whenEqualsAreNotRedefined() {
        UserHashCode firstKey = new UserHashCode("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        UserHashCode secondKey = new UserHashCode("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        Map<UserHashCode, Object> map = new HashMap<>();
        System.out.println(firstKey.hashCode() == secondKey.hashCode());
        map.put(firstKey, "first value");
        map.put(secondKey, "second value");
        System.out.println(map);
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenHashCodeAreNotRedefined() {
        UserEquals firstKey = new UserEquals("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        UserEquals secondKey = new UserEquals("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        Map<UserEquals, Object> map = new HashMap<>();
        map.put(firstKey, "first value");
        map.put(secondKey, "second value");
        System.out.println(map);
        System.out.println(firstKey.equals(secondKey));
        assertThat(map.size(), is(2));
        assertThat(map.get(firstKey), is("first value"));
        assertThat(map.get(secondKey), is("second value"));
    }

    @Test
    public void whenEqualsAndHashCodeAreRedefined() {
        UserBothMethods firstKey = new UserBothMethods("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        UserBothMethods secondKey = new UserBothMethods("qwe", 12, new GregorianCalendar(2020, Calendar.MARCH, 20));
        Map<UserBothMethods, Object> map = new HashMap<>();
        map.put(firstKey, "first value");
        map.put(secondKey, "second value");
        System.out.println(map);
        System.out.println(firstKey.equals(secondKey));
        System.out.println(firstKey.hashCode() == secondKey.hashCode());
        assertThat(map.size(), is(1));
        assertThat(map.get(firstKey), is("second value"));
        assertThat(map.get(secondKey), is("second value"));
    }
}
