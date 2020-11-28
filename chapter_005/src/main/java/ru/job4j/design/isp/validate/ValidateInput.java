package ru.job4j.design.isp.validate;

public class ValidateInput implements InputRange, InputString {
    ConsoleInput consoleInput;

    public ValidateInput(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

    @Override
    public String askStr(String question) {
        return consoleInput.askStr(question);
    }

    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = consoleInput.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
