package ru.job4j.control;

public class User {

    private String name;
    private long id;

    public User(long id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
