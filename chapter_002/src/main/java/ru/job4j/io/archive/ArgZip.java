package ru.job4j.io.archive;

import java.io.File;
import java.util.Arrays;

/**
 * Класс метод для валидации параметров запуска архиватора Zip.
 */
public class ArgZip {
    /**
     * Конструктор принимает массив параметров.
     */
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    /**
     * Проверка количества параметров и синтаксиса их записи.
     * Проверка валидности директории, которую будем архивировать.
     * Проверка формата конечного архива.
     * @return - true, если ни одного исключения не упало.
     */
    private boolean valid() {
        if (args.length != 6 || !args[0].equals("-d") || !args[2].equals("-e") || !args[4].equals("-o")) {
            throw new IllegalArgumentException(String.format("The arguments to run are not enough.%nPlease observe the following syntax:%n-d path -e .excludeFormat -o name.zip%n%s", Arrays.toString(args)));
        }
        File directory = new File(args[1]);
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("-d directory not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("-d is not directory %s", directory.getAbsoluteFile()));
        }
        if (!args[5].endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("-o is not zip format%n%s", args[5]));
        }
        return true;
    }

    /**
     * Три метода - геттера.
     * @return - параметры, прошедшие валидацию.
     */
    public String directory() {
        return valid() ? args[1] : "-1";
    }

    public String exclude() {
        return valid() ? args[3] : "-1";
    }

    public String output() {
        return valid() ? args[5] : "-1";
    }
}
