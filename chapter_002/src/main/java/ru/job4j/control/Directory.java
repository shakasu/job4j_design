package ru.job4j.control;

import java.util.Stack;

class Shell {

    /**
     * Стек для хранения пути.
     */
    private final Stack<String> path = new Stack<>();

    /**
     * Добавленная команда, которая будет обрабатываться в ключевом методе processing().
     */
    private String addedPath;

    /**
     * Константы.
     */
    private final static String SEPARATOR = "/";
    private final static String BACKWARD = "..";
    private final static String HOME = "//";
    private final static String EMPTY = "";
    private final static String REGEX_FOR_NORMALIZATION = "[. /]";


    /**
     * Метод обработки команды.
     */
    private void processing() {
        // если команда содержит возврат, то удалим одну директорию
        if (addedPath.contains(BACKWARD)) {
            if (!path.empty()) {
                //либо из конечного пути
                path.pop();
            } else {
                //либо из команды, так как конечный путь в данный момент пустой
                addedPath = EMPTY;
            }
            // в конце данной обработки удаляем из команды символы
        } else {
            // иначе, если в начале команды есть возврат в корень
            if (addedPath.contains(HOME)) {
                //то просто очищаем путь
                path.clear();
            }
            // в конце данной обработку удаляем из команды символы
        }
        normalize();
        append();
    }

    /**
     * Метод нормализует путь, то есть убирает из него лишние символы.
     */
    private void normalize() {
        addedPath = addedPath.replaceAll(REGEX_FOR_NORMALIZATION, EMPTY);
    }

    /**
     * Метод дополняет итоговый путь (path) тем, что осталось от только что обработанной команды (addedPath).
     */
    private void append() {
        for (String item : addedPath.split(SEPARATOR)) {
            if (!item.equals(EMPTY)) {
                path.add(item);
            }
        }
    }

    /**
     * Метод принимает параметры команды change directory и инициализирует обработку запроса.
     * @param path - параметры команды.
     * @return - экземпляр абстрактной директории.
     */
    Shell cd(final String path) {
        addedPath = path;
        processing();
        return this;
    }

    /**
     * Метод возвращает текущий путь.
     * @return - текущий путь.
     */
    public String path() {
        StringBuilder result = new StringBuilder(SEPARATOR);
        if (path.size() != 0) {
            for (String item : path) {
                result.append(item);
                result.append(SEPARATOR);
            }
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}

public class Directory {
    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }
}