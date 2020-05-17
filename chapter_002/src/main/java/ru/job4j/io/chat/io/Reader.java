package ru.job4j.io.chat.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Внутренний класс логики.
 * Читает текстовый файл, который превращает в список слов,
 * которыми отвечает бот.
 */
public class Reader {
    public final String path;
    private final List<String> pull = new ArrayList<>();

    /**
     * Класс геттер.
     * @return - возвращает случайное слово из списка.
     */
    public String random() {
        return pull.get((int) (Math.random() * pull.size()));
    }

    /**
     * Конструктор принимает путь файла, и сразу же перерабатывает его в список.
     * @param path - путь файла.
     */
    public Reader(String path) {
        this.path = path;
        load();
    }

    /**
     * Метод перерабатывает строки текстового файла и формирует список доступных слов.
     */
    private void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(pull::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
