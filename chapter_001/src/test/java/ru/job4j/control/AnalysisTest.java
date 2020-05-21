package ru.job4j.control;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.control.Analysis;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {
    List<Analysis.User> previous = new ArrayList<>();
    List<Analysis.User> current = new ArrayList<>();
    Analysis.User user1 = new Analysis.User(1, "first");
    Analysis.User user2 = new Analysis.User(2, "second");
    Analysis.User user3 = new Analysis.User(3, "third");
    Analysis.User userSameIdAndDiffName = new Analysis.User(2, "forth");
    Analysis.User newUser = new Analysis.User(5, "fifth");

    @Before
    public void setUp() {
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
    }

    @Test
    public void whenAdded() {
        current.add(user1);
        current.add(user2);
        current.add(user3);
        current.add(newUser);
        Analysis.Info expected = new Analysis.Info(1, 0, 0);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenChanged() {
        current.add(user1);
        current.add(userSameIdAndDiffName);
        current.add(user3);
        Analysis.Info expected = new Analysis.Info(0, 1, 0);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleted() {
        current.add(user1);
        Analysis.Info expected = new Analysis.Info(0, 0, 2);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddedAndChanged() {
        current.add(user1);
        current.add(userSameIdAndDiffName);
        current.add(user3);
        current.add(newUser);
        Analysis.Info expected = new Analysis.Info(1, 1, 0);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddedAndDeleted() {
        current.add(user2);
        current.add(user3);
        current.add(newUser);
        Analysis.Info expected = new Analysis.Info(1, 0, 1);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeletedAndChanged() {
        current.add(userSameIdAndDiffName);
        current.add(user3);
        Analysis.Info expected = new Analysis.Info(0, 1, 1);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddedAndChangedAndDeleted() {
        current.add(userSameIdAndDiffName);
        current.add(user3);
        current.add(newUser);
        Analysis.Info expected = new Analysis.Info(1, 1, 1);
        Analysis.Info result = Analysis.diff(previous, current);
        assertThat(result, is(expected));
    }
}