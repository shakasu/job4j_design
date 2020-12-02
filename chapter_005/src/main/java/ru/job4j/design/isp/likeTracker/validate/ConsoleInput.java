package ru.job4j.design.isp.likeTracker.validate;

import java.util.Scanner;

public class ConsoleInput implements InputRange, InputString {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question, int max) {
        int select = Integer.parseInt(askStr(question));
        if (select < 0 || select > max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        } 
        return select;
    }
}