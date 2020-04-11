package ru.job4j.list;

public class SimpleStack<T> {
    private SimpleLinkedList<T> linked = new SimpleLinkedList<>();

    public T poll() {
        T result = linked.iterator().next();
        linked.delete();
        return result;
    }

    public void push(T value) {
        linked.add(value);
    }

    public int size() {
        return linked.size();
    }
}
