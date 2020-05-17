package ru.job4j.io.chat.io;

import java.io.File;
import java.util.Arrays;

/**
 * Внутренний класс логики.
 * Класс осуществляет валидацию параметров запуска приложения.
 * шаблон:
 * -d C:\projects\job4j_design\pull.txt -o output.txt
 * первый параметр - список слов, этот файл должен быть формата *.txt,
 * и он должен существовать на момент запуска программы.
 * второй параметр - файл для записи диалога, этот файл должен быть формата *.txt.
 */
public class Validator {
    private final String[] args;

    /**
     * Конструктор получает массив параметров.
     * @param args - параметры.
     */
    public Validator(String[] args) {
        this.args = args;
    }

    /**
     * метод валидации:
     * проверка на правильность синтаксиса параметров запуска,
     * проверка, что список слов существует,
     * проверка, что он txt,
     * проверка, что файл для записи txt.
     * @return - true, если ни одно исключение не упало.
     */
    private boolean valid() {
        if (args.length != 4 || !args[0].equals("-d") || !args[2].equals("-o")) {
            throw new IllegalArgumentException(String.format("The arguments to run are not enough.%nPlease observe the following syntax:%n-d path -o name.txt%n%s", Arrays.toString(args)));
        }
        File directoryInp = new File(args[1]);
        if (!directoryInp.exists()) {
            throw new IllegalArgumentException(String.format("-d txt file not exist %s", directoryInp.getAbsoluteFile()));
        }
        if (!args[1].endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("-d is not txt format%n%s", args[3]));
        }
        if (!args[3].endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("-o is not txt format%n%s", args[3]));
        }
        return true;
    }

    /**
     * геттеры для списка слов и файла для записи диалога
     * @return - пути файлов.
     */
    public String directory() {
        return valid() ? args[1] : "-1";
    }

    public String output() {
        return valid() ? args[3] : "-1";
    }
}
