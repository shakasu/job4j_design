package ru.job4j.searcher.logic;

import java.io.File;
import java.util.Arrays;

/**
 * Класс для валидации параметров запуска.
 */
public class Validator {
    /**
     * Массив параметров.
     */
    private final String[] args;
    /**
     * Константы для читаемости параметров.
     */
    private final String directory;
    private final String expression;
    private final String type;
    private final String output;

    /**
     * Конструктор принимает массив и инициализирует константы.
     * @param args - параметры запуска.
     */
    public Validator(String[] args) {
        this.args = args;
        this.directory = args[1];
        this.expression = args[3];
        this.type = args[4];
        this.output = args[6];
    }

    /**
     * Валидация параметров запуска:
     * Проверка синтаксиса запроса,
     * Проверка, что директория существует и является директорией.
     * Проверка типа поиска (что выбран один из трех существующих).
     * Проверка, что вывод - текстовый файл.
     * @return - true, если ни одно исключение не упало.
     */
    private boolean valid() {
        if (args.length != 7
                || !args[0].equals("-d")
                || !args[2].equals("-n")
                || !args[5].equals("-o")) {
            throw new IllegalArgumentException(
                    String.format("The arguments not defined.%nPlease observe the following syntax:%njava -jar find.jar -d c:/ -n *.txt -m -o log.txt%n-d - search directory,%n-n - file, mask or regular expression,%n-o - search result, should be *.txt.%nYour request:%n%s",
                            Arrays.toString(args)
                    )
            );
        }
        File directory = new File(this.directory);
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("-d directory not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("-d is not directory %s", directory.getAbsoluteFile()));
        }
        if (!(type.equals("-m") || type.equals("-f") || type.equals("-r"))) {
            throw new IllegalArgumentException(String.format("%s invalid search type%n-m - mask (name.* or *.txt), -f - full name match (name.txt), -r - regular expression ().", type));
        }
        if (!output.endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("-o is not *.txt format%n%s", output));
        }
        return true;
    }

    /**
     * Четыре метода - геттера.
     * @return - для параметров прошедших валидацию.
     */
    public String directory() {
        return valid() ? directory : "-1";
    }

    public String expression() {
        return valid() ? expression : "-1";
    }

    public String type() {
        return valid() ? type : "-1";
    }

    public String output() {
        return valid() ? output : "-1";
    }
}


