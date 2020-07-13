package ru.job4j.tracker.start;

import ru.job4j.tracker.logic.SqlTracker;
import ru.job4j.tracker.logic.Store;
import ru.job4j.tracker.validate.ConsoleInput;
import ru.job4j.tracker.validate.Input;
import ru.job4j.tracker.validate.ValidateInput;

import java.util.function.Consumer;

public class StartUI {
    private final Input input;
    private final Store tracker;
    private final Consumer<String> output;

    public StartUI(Input input, Store tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions();
        boolean run = true;
        while (run) {
            menu.show();
            run = menu.select(input.askInt("Select: ", menu.size()));
        }
    }

    public static void main(String[] args) {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            new StartUI(new ValidateInput(new ConsoleInput()), tracker, System.out::println).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}