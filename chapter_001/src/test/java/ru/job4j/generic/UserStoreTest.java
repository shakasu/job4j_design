package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {
    UserStore userStore;
    User user1 = new User("1");
    User user2 = new User("2");
    User user3 = new User("3");
    User user4 = new User("4");

    @Before
    public void setUp() {
        userStore = new UserStore(3);
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    @Test
    public void add() {
        assertThat(userStore.findById("1"), is(user1));
        assertThat(userStore.findById("2"), is(user2));
        assertThat(userStore.findById("3"), is(user3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void replace() {
        userStore.replace("1", user4);
        assertThat(userStore.findById("4"), is(user4));
        userStore.findById("1");

    }

    @Test
    public void delete() {
        userStore.delete("1");
        assertThat(userStore.findById("2"), is(user2));
        assertThat(userStore.findById("3"), is(user3));
        userStore.add(user4);
        assertThat(userStore.findById("4"), is(user4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void storageIsFull() {
        userStore.add(user4);
    }
}
