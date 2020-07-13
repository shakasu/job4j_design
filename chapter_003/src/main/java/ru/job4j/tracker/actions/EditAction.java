package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.logic.Item;

import java.util.function.Consumer;

public class EditAction extends BaseAction {
    public EditAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String idItem =  input.askStr("Enter the Id of the old item:");
        String newItemName =  input.askStr("Enter the name of the new item:");
        int newItemPriority =  input.askInt("Enter the priority of the new item:");
        Item newItem = new Item(newItemName, newItemPriority);
        if (tracker.replace(idItem, newItem)) {
            output.accept(String.format("Editing successfully done - %s Id - %s%n", newItem.getName(), newItem.getId()));
        } else {
            output.accept(String.format("Item not found"));
        }
        return true;
    }
}
