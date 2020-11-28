package ru.job4j.design.isp.actions;

import ru.job4j.design.isp.validate.InputString;

import java.util.function.Consumer;

public class DeleteAction extends BaseAction {
    public DeleteAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(InputString inputString, Consumer<String> output) {
        System.out.println("executing delete action");
        return true;
    }
}
