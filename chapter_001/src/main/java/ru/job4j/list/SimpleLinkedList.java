package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private Node<E> head;
    private int modCount = 0;

    /**
     * Метод возвращает специально обученный итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> node = head;
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                node = node.next;
                index++;
                return value;
            }
        };
    }

    /**
     * Метод значение вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.head;
        this.head = newLink;
        this.size++;
        this.modCount++;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.value;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int size() {
        return this.size;
    }

    /**
     * Метод удаления первого элемент в списке.
     */
    public E delete() {
        E result = head.value;
        head = head.next;
        size--;
        this.modCount++;
        return result;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }
}
