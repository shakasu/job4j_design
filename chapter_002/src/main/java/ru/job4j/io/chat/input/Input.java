package ru.job4j.io.chat.input;

/**
 * Интерфейс для отвязывания от конечной реализации.
 */
public interface Input {
    String askStr(String word);
}
