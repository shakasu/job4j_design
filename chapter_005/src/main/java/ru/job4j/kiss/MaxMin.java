package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, false);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, true);
    }

    private <T> T compare(List<T> value, Comparator<T> comparator, boolean comparison) {
        T result = value.get(0);
        int i = comparison ? 1 : -1;
        for (T cell : value) {
            result = (comparator.compare(result, cell) == i) ? cell : result;
        }
        return result;
    }
}