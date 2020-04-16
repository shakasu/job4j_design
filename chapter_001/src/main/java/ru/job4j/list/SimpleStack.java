package ru.job4j.list;

public class SimpleStack<T> {
    private SimpleLinkedList<T> linked = new SimpleLinkedList<>();

    public T poll() {
        return linked.delete();
    }

    public void push(T value) {
        linked.add(value);
    }

    public int size() {
        return linked.size();
    }
}
