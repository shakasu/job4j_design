package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    @Test
    public void queue() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);
        assertThat(simpleQueue.poll(), is(1));
        simpleQueue.push(4);
        assertThat(simpleQueue.poll(), is(2));
        simpleQueue.push(5);
        assertThat(simpleQueue.poll(), is(3));
        assertThat(simpleQueue.poll(), is(4));
        simpleQueue.push(6);
        assertThat(simpleQueue.poll(), is(5));
        assertThat(simpleQueue.poll(), is(6));
    }
}
