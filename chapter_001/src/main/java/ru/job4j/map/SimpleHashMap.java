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
     * Массив пар и голова односвязного списка для текущего и нового массива.
     */
    private Node<K, V>[] values;
    private Node<K, V> head;

    /**
     * cursor считает, сколько элементов добавлено в множество.
     * modCount фиксирует количество изменений в структуре.
     */
    private int cursor;
    private int modCount;

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
     * Метод добавляет пару в структуру и возвращает истинность операции.
     * @param key - ключ.
     * @param value - значение.
     * @return - истинность добавления нового элемента в множество.
     */
    public boolean insert(K key, V value) {
        growCheck();
        boolean result = putVal(key, value, this.values);
        if (result) {
            this.cursor++;
            this.modCount++;
        }
        return result;
    }

    /**
     * Метод добавляет пару в массив, связывая ячейки в порядке добавления.
     * @param key - ключ.
     * @param value - значение.
     * @param values - массив, в который добавится пара.
     * @return - истинность добавления нового элемента в массив.
     */
    private boolean putVal(K key, V value, Node<K, V>[] values) {
        boolean result = !doesThisKeyExist(key, values);
        if (result && key != null) {
            Node<K, V> newNode = new Node<>(key, value, null);
            if (head == null) {
                head = newNode;
            } else {
                Node<K, V> tail = head;
                while (tail.next != null) {
                    tail = tail.next;
                }
                tail.next = newNode;
            }
            values[hash(key)] = newNode;
        }
        return  result;
    }

    /**
     * Метод проверяет на ненулевое возвращаемое значение и на наличие данного ключа в множестве.
     * Возможен null, если такого ключа в множестве нет, или вызываемый элемент - null.
     * @param key - ключ.
     * @return - значение соответствующее данному ключу.
     */
    public V get(K key) {
        return (doesThisKeyExist(key, this.values) && this.values[hash(key)] != null) ? this.values[hash(key)].value : null;
    }

    /**
     * Если данный ключ существует, то метод его удалит его из массива, пересчитает размер массива и сдвинет счетчики.
     * @param key - ключ.
     * @return - истинность удаления элемента.
     */
    public boolean delete(K key) {
        boolean result = doesThisKeyExist(key, this.values);
        if (result) {
            modCount++;
            cursor--;
            this.values[hash(key)] = null;
            resize(size() - 1);
        }
        return result;
    }

    /**
     * key.hashCode() является 10-значным число, что больше, чем длина массива,
     * поэтому его надо привести так, чтобы он остался уникальным для актуального размера массива пар.
     * @param key ключ элемента.
     * @return - индекс в который будет помещен элемент в глобальном массиве.
     */
    private int hash(K key) {
        return key.hashCode() & (size() - 1);
    }

    /**
     * Проверка необходимости увеличить длину массива, если количество элементов станет
     * равным (длина массива) * (коэффициент загрузки),
     * то создается в два раза более длинный массив и в него перемещаются элементы.
     */
    private void growCheck() {
        if (cursor + 1 == this.loadFactor * size()) {
            resize(2 * size());
        }
    }

    /**
     * Метод перемещает элементы из старого массива,
     * новый с новым размером, с пересчетом hash().
     * @param newSize - новый размер массива.
     */
    private void resize(int newSize) {
        Node<K, V>[] intermediateValues = new Node[newSize];
        for (Node<K, V> node : this.values) {
            if (node != null) {
                putVal(node.key, node.value, intermediateValues);
            }
        }
        this.values = intermediateValues;
    }

    /**
     * @return возвращает специальный итератор, реализованный связным списком.
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
     * @return - количество элементов в множестве.
     */
    public int elementCount() {
        return cursor;
    }

    /**
     * @return - количество ячеек в множестве.
     */
    public int size() {
        return this.values.length;
    }

    /**
     * Цикл ищет такой же ключ в указанном массиве.
     * @param key - ключ.
     * @return возвращает истинность наличия данного ключа в множестве.
     */
    private boolean doesThisKeyExist(K key, Node<K, V>[] values) {
        boolean result = false;
        for (Node<K, V> node : values) {
            if (node != null && node.key.equals(key)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Класс является ячейкой пары ключ-значение, так же хранит в себе ссылку на следующую пару.
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