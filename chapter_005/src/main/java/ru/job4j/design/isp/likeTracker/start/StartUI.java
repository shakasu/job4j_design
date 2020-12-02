package ru.job4j.design.isp.likeTracker.start;


import ru.job4j.design.isp.likeTracker.validate.*;


import java.util.function.Consumer;

public class StartUI {
    ValidateInput validateInput;
    private final Consumer<String> output;

    public StartUI(ValidateInput validateInput, Consumer<String> output) {
        this.validateInput = validateInput;
        this.output = output;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(validateInput, output);
        menu.fillActions();
        boolean run = true;
        while (run) {
            menu.show();
            run = menu.select(validateInput.askInt("Select: ", menu.size()));
        }
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), System.out::println).init();
    }
}