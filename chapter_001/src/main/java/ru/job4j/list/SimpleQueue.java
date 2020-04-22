package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> input = new SimpleStack<>();
    private SimpleStack<T> output = new SimpleStack<>();

    public  void push(T value) {
        input.push(value);
    }

    public T poll() {
        if (output.size() == 0) {
            while (input.size() > 0) {
                output.push(input.poll());
            }
        }
        return output.poll();
    }
}
