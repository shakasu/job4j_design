package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int size;

    private Node<E> first;

    private int modCount = 0;

    /**
     * Метод возвращает специально обученный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int index = 0;

            private final int expectedModCount = modCount;

            private void checkCME() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkCME();
                return index != size;
            }

            @Override
            public E next() {
                checkCME();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(index++);
            }
        };
    }

    /**
     * Метод значение вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int size() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
