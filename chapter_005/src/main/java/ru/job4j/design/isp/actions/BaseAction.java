package ru.job4j.design.isp.actions;

public abstract class BaseAction implements UserAction {
    private final String name;
    private final int key;

    protected BaseAction(final int key, final String name) {
        this.name = name;
        this.key = key;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s%n", this.key, this.name);
    }
}
