package ru.job4j.design.isp.likeTracker.validate;

public class StubInput implements InputRange, InputString {

    private final String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }
    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    @Override
    public int askInt(String question, int max) {
        return Integer.parseInt(askStr(question));
    }
}
