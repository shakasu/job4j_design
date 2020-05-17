package ru.job4j.io.chat.input;

/**
 * Класс - затычка, необходим для тестирования приложения.
 */
public class StabInput implements Input {
    private final String[] answers;
    private int cursor = 0;

    public StabInput(String[] answer) {
        this.answers = answer;
    }

    @Override
    public String askStr(String question) {
        return answers[cursor++];
    }
}
