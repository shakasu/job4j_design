package ru.job4j.io.chat.input;

import java.util.Scanner;

/**
 * Основная реализация Input, подключена к утилите Scanner,
 * необходим для чтения ввода пользователя в консоль.
 */
public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String word) {
        System.out.println(word);
        return scanner.nextLine();
    }
}
