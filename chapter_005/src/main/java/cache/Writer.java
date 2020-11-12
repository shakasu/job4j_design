package cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;

/**
 * Класс производит запись строчки в файл.
 */
public class Writer {
    private final Path result;

    private static final Logger LOG = LoggerFactory.getLogger(Writer.class.getName());

    /**
     * Конструктор создает файл по заданному пути.
     * @param path - путь.
     */
    public Writer(String path) {
        this.result = Path.of(path);
    }

    /**
     * Метод производит построчную запись в файл.
     * Используется специальный конструктор FileOutputStream,
     * в котором второй параметр отвечает за то,
     * чтобы файл не переписывался.
     * @param line - сточка, которая будет записана.
     */
    public void record(String line) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(
                                result.toFile().getAbsoluteFile(),
                                true
                        )
                )
        )) {
            out.write(String.format("%s%n", line));
        } catch (Exception e) {
            LOG.error("Writer error", e);
        }
    }
}
