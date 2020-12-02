package ru.job4j.design.isp.likeTracker.actions;

import ru.job4j.design.isp.likeTracker.validate.InputString;

import java.util.function.Consumer;

public interface UserAction {
    int key();

    String info();

    boolean execute(InputString inputString, Consumer<String> output);
}
