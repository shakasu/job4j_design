package ru.job4j.design.isp.anotherDirection;

import java.util.List;

/**
 * Base model for menu's element.
 */

public class Item implements Comparable<Item>{
    private String name;
    private final Action action;
    private final List<Item> items;

    public Item(String name, Action action, List<Item> items) {
        this.name = name;
        this.action = action;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public int compareTo(Item o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("%s%n", name);
    }
}
