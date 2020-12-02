package ru.job4j.design.isp.anotherDirection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    Menu menu;
    Item item;
    Item newItem;
    Item newSubItem;
    EchoAction action;

    @Before
    public void setUp() {
        menu = new Menu();
        action = new EchoAction();
        item = new Item("1. one", action, new ArrayList<>());
        newItem = new Item("2. two", action, new ArrayList<>());
        newSubItem = new Item("1.2. between one & two", action, new ArrayList<>());
        menu.add("", item);
        menu.add("", newItem);
        menu.add(item.getName(), newSubItem);
    }

    @Test
    public void add() {
        assertThat(menu.get(item.getName()), is(item));
    }

    @Test
    public void print() {
        menu.menuForming();
        String expectedMenu = String.format("%n0 - 1. one%n%n1 - 1.2. between one & two%n%n2 - 2. two%n");
        assertThat(menu.print(), is(expectedMenu));
        assertThat(menu.size(), is(3));
    }

    @Test
    public void bypassFindByName() {
        AtomicReference<Item> result = new AtomicReference<>();
        menu.bypass(i -> {
            if (i.getName().startsWith("1.2")) {
                result.set(i);
            }
        });
        assertThat(result.get(), is(newSubItem));
    }


}
