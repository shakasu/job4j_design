package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Коллекция SimpleHashMap реализуется на массиве пар ключ-значение,
 * между парами существует связь по очереди добавления,
 * реализованная в классе Node.
 * @param <K> - ключ.
 * @param <V> - значение.
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
    /**
     * Массив пар и голова односвязаного списка.
     */
    private Node<K, V>[] values;
    private Node<K, V> head;

    /**
     * cursor считает, сколько элементов добавлено в множество.
     * modCount фиксирует колличество изменений в структуре.
     */
    private static int cursor;
    private static int modCount;

    /**
     * Константы: начальная емкость и коэффициент загрузки.
     */
    private final int startCapacity = 16;
    private final double loadFactor = 0.75;

    /**
     * конструктор инициализирует массив начальной емкости и задает нули двум счетчикам.
     */
    public SimpleHashMap() {
        this.values = new Node[this.startCapacity];
        modCount = 0;
        cursor = 0;
    }

    /**
     * В данном методе 4 вспомогательных, они специально вынесены для повышения читаемости.
     * putInLinkedList() - необходим исключительно для реализации итератора, иначе между элементами
     * нет никакой связи.
     * @param key - ключ.
     * @param value - значение.
     * @return - истинность добавления нового элемента в множество.
     */
    public boolean insert(K key, V value) {
        boolean result = !doesThisKeyExist(key);
        if (result) {
            Node<K, V> newNode = new Node<>(key, value, null);
            putInLinkedList(newNode);
            putInGlobalArray(newNode, key);
            growCheck();
        }
        return result;
    }

    /**
     * Проверка на ненулевое возвращаемое значение и на наличие данного ключа в множестве.
     * Возможен null, если такого ключа в множетве нет, или вызываемый элемент - null.
     * @param key - ключ.
     * @return - значение соответсвующее данному ключу.
     */
    public V get(K key) {
        return (doesThisKeyExist(key) && this.values[hash(key)] != null) ? this.values[hash(key)].value : null;
    }

    /**
     * Если данный ключ существует, то метод его удалит нативным методом копирования со сдвигом и сдвинет счетчики.
     * @param key - ключ.
     * @return - истинность удаления элемента.
     */
    public boolean delete(K key) {
        boolean result = doesThisKeyExist(key);
        if (result) {
            modCount++;
            cursor--;
            int index = hash(key);
            System.arraycopy(this.values, index, this.values, index - 1, this.values.length - index);
        }
        return result;
    }

    /**
     * @return возвращает специальный итератор, реализованный связаным списком.
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            /**
             * expectedModCount - запоминает состояние структуры системы.
             * node - элемент для манипулирования ссылками.
             */
            private final int expectedModCount = modCount;
            Node<K, V> node = head;

            /**
             * Если после появления итератора были изменения в структуре (вставка или удаление элементов),
             * то при использовании этого метода будет выкинуто исключение.
             * @return истинность наличия следующего элемента.
             */
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            /**
             * Возвращает следующий элемент множества, если такого нет, упадет исключение.
             * Передается ссылка на следующий элемент.
             * @return - следующий элемент перечисления.
             */
            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                V value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     *
     * @return - колличество элементов в множестве.
     */
    public int elements() {
        return cursor;
    }

    /**
     *
     * @return - колличество ячеек в множестве.
     */
    public int size() {
        return this.values.length;
    }

    /**
     * Метод добаляет элемент в связанный список,
     * теперь у каждого элемента будет ссылка на следующий.
     * @param newNode - новый элемент в связанном списке.
     */
    private void putInLinkedList(Node<K, V> newNode) {
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<K, V> tail = this.head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
    }

    /**
     * Помещает элемент в массив на специальный индекс, высчитывающийся из хеш-кода ключа,
     * обеспечивается фиксированное время вставки и получения.
     * @param newNode - новый элемент в глобальном массиве.
     * @param key - ключ элемента.
     */
    private void putInGlobalArray(Node<K, V> newNode, K key) {
        this.values[hash(key)] = newNode;
        modCount++;
        cursor++;
    }

    /**
     * key.hashCode() является 10-значным число, что больше, чем длина глобального массива,
     * поэтому его надо обрубить так, чтобы он остался уникальным для актуального размера глобального массива.
     * Этот метод имеет существенной недостаток, который не дает полноценно работать с коллекцией.
     * При увелицении размера, ранее добавленные элементы будут потеряны, так как, метод get() завязан на
     * hash(), а индекс ранее добавленных элементов высчитывался при прошлой длине массива.
     * Вторая версия данного метода:
     * Создать счетчик увеличений массива, фиксировать текущее кол-во элементов в конструкторе Node<>.
     * А формулу хеша вместо size() -> (startCapacity * growCounter).
     * В таком случае будет ошибка переполнения стека, тк два метода обращаются друг другу
     * в рекурсивной форме.
     * Третий вариант решения этой проблемы - реализовать защиту от коллизий, создав односвязный список,
     * в каждом элементе массива, однако это противоречит поставленой задаче.
     * @param key ключ элемента.
     * @return - индекс в который будет помещен элемент в глобальном массиве.
     */
    private int hash(K key) {
        return key.hashCode() % size();
    }

    /**
     * Проверка необходимости увеличить длину массива, если количество элементов станет равным (длина массива) * (коэффициент загрузки),
     * то создается в два раза более длинный массив и в него комируются элементы.
     * После новый массив становится глобальным.
     */
    private void growCheck() {
        if (cursor >= this.loadFactor * size()) {
            Node<K, V>[] objects = new Node[2 * size()];
            System.arraycopy(this.values, 0, objects, 0, size());
            this.values = objects;
        }
    }

    /**
     * Цикл ищет такой же ключ в массиве.
     * @param key - ключ.
     * @return возвращает истиность наличия данного ключа в множестве.
     */
    private boolean doesThisKeyExist(K key) {
        boolean result = false;
        for (Node<K, V> node : this.values) {
            if (node != null && node.key.equals(key)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Класс является ячейкой пары ключ-значение, так же хранит в себе ссылку на следущую пару.
     * @param <K> - ключ.
     * @param <V> - значение.
     */
    private final static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        private Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}