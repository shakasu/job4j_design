package ru.job4j.io.chat.start;

import ru.job4j.io.chat.io.Reader;
import ru.job4j.io.chat.io.Writer;

/**
 * Внешний класс для объединения всей логики.
 */
public class Response {
    private final Reader reader;
    private final Writer writer;

    /**
     * случайное слово, для ответа бота
     */
    private String randomWord;
    /**
     * переключатель для ответа бота.
     * когда он true, бот не отвечает и запись в файл не происходит.
     */
    private boolean stopSwitch = false;
    /**
     * команды
     */
    final private String go = "продолжить";
    final private String stop = "стоп";
    final private String fin = "закончить";

    public Response(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * метод описывает реакию бота.
     * @param command - ввод пользователя.
     * @return - false, если команда "закончить"
     */
    public boolean reaction(String command) {
        boolean result = commandLogic(command);
        writer.record(formatting(command));
        randomWord = reader.random();
        if (!(stopSwitch || command.equals(fin))) {
            writer.record(formatting(randomWord));
        }
        return result;
    }

    /**
     * метод описывает порядок ответа бота.
     * @return - если ранее было введено "Стоп",
     * то вернется пустая строка,
     * иначе случайно слово.
     */
    public String stopOrder() {
        return (!stopSwitch) ? randomWord : " ";
    }

    /**
     * логика обработки пользовательского ввода.
     * @param command - ввод
     * @return - false, если команда "закончить"
     */
    private boolean commandLogic(String command) {
        boolean result = true;
        if (isItCommand(command)) {
            if (command.equals(stop) && !stopSwitch) {
                stopSwitch = true;
            }
            if (command.equals(go) && stopSwitch) {
                stopSwitch = false;
            }
            if (command.equals(fin)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * является ли ввод командой
     * @param command - ввод
     * @return - boolean
     */
    private boolean isItCommand(String command) {
        return command.equals(go)
                || command.equals(stop)
                || command.equals(fin);
    }

    /**
     * форматирование для вывода в консоль.
     * @param command - запись.
     * @return запись с переносом на следующую строку.
     */
    private String formatting(String command) {
        return String.format("%n%s", command);
    }
}
