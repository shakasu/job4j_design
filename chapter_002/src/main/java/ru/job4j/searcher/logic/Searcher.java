package ru.job4j.searcher.logic;

import ru.job4j.io.base.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс осуществляет поиск в файловой системе по предикату.
 */
public class Searcher {
    private final Path root;
    private final Predicate<Path> predicate;

    /**
     * Конструктор получает стартовую директорию и предикат.
     * @param root - директория.
     * @param predicate - предикат.
     */
    public Searcher(Path root, Predicate<Path> predicate) {
        this.root = root;
        this.predicate = predicate;
    }

    /**
     * Класс SearchFiles реализует интерфейс FileVisitor<Path> и переопределяет метод visitFile.
     * В котором при соответствию предикату, файл добавляется в список.
     * Метод getPaths() возвращает этот список.
     * @return - список файлов, соответствущий предикату.
     * @throws IOException - при ошибках ввода-вывода.
     */
    public List<Path> find() throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
