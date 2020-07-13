package ru.job4j.tracker.start;

import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final Input input;
    private final Store tracker;
    private final Consumer<String> output;

    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Store tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void fillActions() {
        this.actions.add(0, new CreateAction(0, "Add new item."));
        this.actions.add(1, new ShowActions(1, "Show all items."));
        this.actions.add(2, new EditAction(2, "Edit item."));
        this.actions.add(3, new DeleteAction(3, "Delete item."));
        this.actions.add(4, new FindActionById(4, "Find by Id."));
        this.actions.add(5, new FindActionByName(5, "Find by name."));
        this.actions.add(6, new ExitAction(6, "Exit"));
    }

    public boolean select(int key) {
        return this.actions.get(key).execute(this.input, this.tracker, output);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    public int size() {
        return this.actions.size();
    }

}

