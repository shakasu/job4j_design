package ru.job4j.tracker.actions;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;

import java.util.function.Consumer;

public class DeleteAction extends BaseAction {
    public DeleteAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String idItem =  input.askStr("Enter the Id of the item to delete.");
        if (tracker.delete(idItem)) {
            output.accept(String.format("Item deleted successfully%nNow item with Id %s not exist%n", idItem));
        } else {
            output.accept(String.format("Item not found"));
        }
        return true;
    }
}
