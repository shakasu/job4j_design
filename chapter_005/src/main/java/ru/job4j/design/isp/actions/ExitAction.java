package ru.job4j.design.isp.actions;

import ru.job4j.design.isp.validate.InputString;

import java.util.function.Consumer;

public class ExitAction extends BaseAction {
    public ExitAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(InputString inputString, Consumer<String> output) {
        return false;
    }
}
