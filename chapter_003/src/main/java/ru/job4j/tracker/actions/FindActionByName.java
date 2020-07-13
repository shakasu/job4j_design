package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.logic.Item;

import java.util.function.Consumer;

public class FindActionByName extends BaseAction {
    public FindActionByName(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        for (Item item : tracker.findByName(input.askStr("Enter the name of the item to search for."))) {
            if (item != null) {
                output.accept(String.format("%s - %s%n", item.getName(), item.getId()));
            }
        }
        return true;
    }
}
