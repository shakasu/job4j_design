package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.logic.Item;

import java.util.function.Consumer;

public class FindActionById extends BaseAction {
    public FindActionById(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String idItem =  input.askStr("Enter the Id of the item to search for.");
        Item requiredItem = tracker.findById(idItem);
        if (requiredItem != null) {
            output.accept(String.format("%s is required element.%n", requiredItem.getName()));
        } else {
            output.accept(String.format("Item not found"));
        }
        return true;
    }
}
