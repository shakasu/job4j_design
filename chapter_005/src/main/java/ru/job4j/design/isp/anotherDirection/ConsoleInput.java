package ru.job4j.design.isp.anotherDirection;

import java.util.Scanner;

/**
 * Input's implementation for input by console.
 */
public class ConsoleInput  implements Input{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int askInt(int max) {
        int select = Integer.parseInt(scanner.nextLine());
        if (select < 0 || select > max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
