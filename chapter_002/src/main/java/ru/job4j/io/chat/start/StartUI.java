package ru.job4j.io.chat.start;

import ru.job4j.io.chat.input.ConsoleInput;
import ru.job4j.io.chat.input.Input;
import ru.job4j.io.chat.io.Reader;
import ru.job4j.io.chat.io.Validator;
import ru.job4j.io.chat.io.Writer;

public class StartUI {
    final private Input input;
    final private Reader reader;
    final private Writer writer;

    public StartUI(Input input, Validator validator) {
        this.input = input;
        this.reader = new Reader(validator.directory());
        this.writer = new Writer(validator.output());
    }

    public void init() {
        Response response = new Response(this.reader, this.writer);
        boolean run = true;
        while (run) {
            run = response.reaction(input.askStr(
                    (writer.isNotFirstRecord())
                            ? response.stopOrder() : "***BEGIN***"
            ));
        }
    }

    /**
     * Параметры запуска для демонстрации.
     * @param args -d C:\projects\job4j_design\pull.txt -o output.txt
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Validator(args)).init();
    }
}
