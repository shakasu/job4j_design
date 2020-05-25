package ru.job4j.searcher.predicates;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Класс - генератор предикатов. Реализован на шаблоне Dispatcher.
 * Идея заменить вложенные if на однозначное соответствие пары ключ-значение в хеш таблице.
 */
public class PredicateDispatcher {

    /**
     * Карта - диспетчер.
     */
    private final Map<String, Predicate<Path>> dispatch;

    /**
     * Константы для ключей.
     */
    private final static String FULL = "-f";
    private final static String REGULAR = "-r";
    private final static String MASK = "-m";

    /**
     * Пользовательское выражение и выбранный тип.
     */
    private final String expression;
    private final String type;

    /**
     * При вызове конструктора подгружается карта с возможными типами поиска.
     * @param expression - выражение.
     * @param type - тип.
     */
    public PredicateDispatcher(String expression, String type) {
        this.expression = expression;
        this.type = type;
        this.dispatch = immutableMap();
    }

    /**
     * Метод генерирует и возвращает immutable карту.
     */
    private Map<String, Predicate<Path>> immutableMap() {
        return Map.of(
                FULL, this.fullName(),
                MASK, this.mask(),
                REGULAR, this.regularExpression()
        );
    }

    /**
     * Генерация предиката по полному имени.
     * Соответственно метод equals(), показывает эквивалентность между строчками.
     * @return - предикат.
     */
    private Predicate<Path> fullName() {
        return p -> fileName(p).equals(expression);
    }

    /**
     * Генерация предиката по регулярному выражению.
     * Соответственно метод matches(), показывает соответствие между regex и именем.
     * @return - предикат.
     */
    private Predicate<Path> regularExpression() {
        return p -> fileName(p).matches(expression);
    }

    /**
     * Генерация предиката по маске (неполное имя).
     * Соответственно метод contains(), показывает наличие в имени файла пользовательского выражения.
     * @return - предикат.
     */
    private Predicate<Path> mask() {
        return p -> fileName(p).contains(expression);
    }

    /**
     * Общий вынесенный код для реализации генераторов предиката.
     * @param p - путь
     * @return - имя файла в системе.
     */
    private String fileName(Path p) {
        return p.toFile().getName();
    }

    /**
     * Самый главный метод возвращающий предикат, сгенерированный по запросу пользователя.
     * @return - готовый предикат.
     */
    public Predicate<Path> produce() {
        return this.dispatch.get(type);
    }
}
