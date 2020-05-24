package ru.job4j.searcher.start;

import ru.job4j.searcher.io.Writer;
import ru.job4j.searcher.logic.Searcher;
import ru.job4j.searcher.logic.Validator;
import ru.job4j.searcher.predicates.PredicateDispatcher;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Класс входа в программу. Собирает все вспомогательные классы.
 */
public class Finder {
    /**
     * writer и searcher - нужны для поиска и записи в главном методе init().
     */
    final private Writer writer;
    final private Searcher searcher;

    /**
     * Для создания экземпляра нужны только параметры запуска, все остальные классы инициализируются через validator.
     * @param validator - объект валидации, который хранит параметры запуска.
     */
    public Finder(Validator validator) {
        this.writer = new Writer(validator.output());
        PredicateDispatcher predicateDispatcher = new PredicateDispatcher(validator.expression(), validator.type());
        this.searcher = new Searcher(Path.of(validator.directory()), predicateDispatcher.produce());

    }

    /**
     * Поиск и запись.
     * @throws IOException - при ошибках ввода-вывода.
     */
    public void init() throws IOException {
        for (Path item : searcher.find()) {
            writer.record(item.getFileName().toString());
        }
    }

    /**
     * Вход в программу.
     * @param args - параметры запуска.
     * @throws IOException - при ошибках ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        new Finder(new Validator(args)).init();
    }
}