package ru.job4j.design.isp.likeTracker.start;

import ru.job4j.design.isp.likeTracker.actions.*;
import ru.job4j.design.isp.likeTracker.actions.UserAction;
import ru.job4j.design.isp.likeTracker.validate.InputString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final InputString inputString;
    private final Consumer<String> output;

    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(InputString inputString, Consumer<String> output) {
        this.inputString = inputString;
        this.output = output;
    }

    public void fillActions() {
        this.actions.add(0, new CreateAction(0, "Add new item."));
        this.actions.add(1, new EditAction(1, "Edit item."));
        this.actions.add(2, new DeleteAction(2, "Delete item."));
        this.actions.add(3, new FindAction(3, "Find by Id."));
        this.actions.add(4, new ExitAction(4, "Exit"));
    }

    public boolean select(int key) {
        return this.actions.get(key).execute(this.inputString, output);
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

