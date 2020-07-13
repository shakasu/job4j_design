package ru.job4j.tracker.logic;

import org.jetbrains.annotations.NotNull;

public class Item implements Comparable<Item> {
    private String id;
    private String name;
    private int priority;

    public Item(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public Item(String name, int priority, String id) {
        this.name = name;
        this.priority = priority;
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NotNull Item another) {
        return Integer.compare(priority, another.priority);
    }
}