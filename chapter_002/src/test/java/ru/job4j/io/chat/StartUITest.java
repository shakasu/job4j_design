package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TemporaryFolder;
import ru.job4j.io.chat.input.Input;
import ru.job4j.io.chat.input.StabInput;
import ru.job4j.io.chat.io.Validator;
import ru.job4j.io.chat.start.StartUI;
import java.io.*;


import static org.junit.Assert.*;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final String ONE = "один";
    /**
     * Имитируем пользовательский ввод в консоль.
     */
    Input input = new StabInput(new String[]{"привет", "стоп", "как дела", "продолжить", "закончить"});
    StringBuilder expected = new StringBuilder();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Вносим ожидаемый диалог в экземпляр StringBuilder.
     */
    @Before
    public void setUp() {
        expected.append("привет");
        expected.append(ONE);
        expected.append("стоп");
        expected.append("как дела");
        expected.append("продолжить");
        expected.append(ONE);
        expected.append("закончить");
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Создаем временные файлы:
     * pull - список доступных слов для ответа бота, заполним его одним словом для однозначного вывода.
     * target - запись конечного диалога, который будет сравниваться с ожидаемым.
     * args - параметры запуска для StartUI.
     */
    @Test
    public void testUI() throws IOException {
        File pull = folder.newFile("dir.txt");
        File target = folder.newFile("out.txt");
        String[] args = new String[]{"-d", pull.getAbsolutePath(), "-o", target.getAbsolutePath()};
        try (PrintWriter out = new PrintWriter(pull)) {
            out.println(ONE);
        }
        StartUI startUI = new StartUI(input, new Validator(args));
        StringBuilder rsl = new StringBuilder();
        startUI.init();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertEquals(0, rsl.compareTo(expected));
    }
}
