package ru.job4j.design.isp.anotherDirection;

/**
 * Input's implementation for validating user's input.
 */
public class ValidateInput implements Input {
    ConsoleInput consoleInput;

    public ValidateInput(ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
    }

    @Override
    public int askInt(int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = consoleInput.askInt(max);
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