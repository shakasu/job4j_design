package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;

import java.util.function.Consumer;

public class ExitAction extends BaseAction {
    public ExitAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        return false;
    }
}
