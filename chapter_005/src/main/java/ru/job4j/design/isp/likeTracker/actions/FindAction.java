package ru.job4j.design.isp.likeTracker.actions;

import ru.job4j.design.isp.likeTracker.validate.InputString;

import java.util.function.Consumer;

public class FindAction extends BaseAction {
    public FindAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(InputString inputString, Consumer<String> output) {
        System.out.println("executing find action");
        return true;
    }
}
