package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface Engine {
    String generate(Predicate<Employee> filter);
}
