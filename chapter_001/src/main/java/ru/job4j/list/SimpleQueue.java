package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> input = new SimpleStack<>();
    private SimpleStack<T> output = new SimpleStack<>();

    public T poll() {
         return output.poll();
    }

    public void push(T value) {
        int outsize = output.size();
        for (int i = 0; i < outsize; i++) {
            input.push(output.poll());
        }
        output.push(value);
        for (int i = 0; i < outsize; i++) {
            output.push(input.poll());
        }
    }
}
