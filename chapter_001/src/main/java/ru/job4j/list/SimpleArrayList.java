package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    private void checkNSEE() {
        if (first == null) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        checkNSEE();
        E result = first.data;
        first = first.next;
        size--;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        checkNSEE();
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
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