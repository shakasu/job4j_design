package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;

import java.util.function.Consumer;

public interface UserAction {
    int key();

    String info();

    boolean execute(Input input, Store tracker, Consumer<String> output);
}
