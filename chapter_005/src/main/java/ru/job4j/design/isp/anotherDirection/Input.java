package ru.job4j.design.isp.anotherDirection;

/**
 * Decoupling from the final implementation of the Scanner (in ConsoleInput).
 * For flexibility in future refactoring.
 */
public interface Input {
    int askInt(int max);
}
