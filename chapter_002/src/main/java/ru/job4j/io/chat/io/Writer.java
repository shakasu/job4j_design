package ru.job4j.io.chat.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;

/**
 * Внутренний класс логики.
 * Класс производит запись диалога в файл.
 */
public class Writer {
    private final Path logs;

    /**
     * Конструктор создает файл по заданному пути.
     * @param path - путь.
     */
    public Writer(String path) {
        this.logs = Path.of(path);
    }

    /**
     * Проверка, что в файле записи нет ни одной записи.
     * @return - true, если файл не пустой.
     */
    public boolean isNotFirstRecord() {
        return logs.toFile().getTotalSpace() != 0;
    }

    /**
     * Метод производит построчную запись в файл.
     * Используется специальный конструктор FileOutputStream,
     * в котором второй параметр отвечает за то,
     * чтобы файл не перезатирался.
     * @param line - сточка, которую запишем.
     */
    public void record(String line) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(
                                logs.toFile().getAbsoluteFile(),
                                true
                        )
                )
        )) {
            out.write(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
