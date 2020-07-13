package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.logic.Item;

import java.util.function.Consumer;

public class ShowActions extends BaseAction {
    public ShowActions(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        for (Item item : tracker.findAll()) {
            if (item != null) {
                output.accept(String.format("%s - %s%n", item.getName(), item.getId()));
            }
        }
        return true;
    }
}
