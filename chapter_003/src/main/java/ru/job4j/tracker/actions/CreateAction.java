package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.logic.Item;

import java.util.function.Consumer;

public class CreateAction extends BaseAction {
    public CreateAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String name = input.askStr("Enter name: ");
        int priority = input.askInt("Enter priority: ");
        Item item = new Item(name, priority);
        tracker.add(item);
        output.accept(String.format("Adding item was successful.%nName - %s and Id - %s of new item%n", item.getName(), item.getId()));
        return true;
    }
}
