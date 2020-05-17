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


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final String LN = System.lineSeparator();
    private static final String ONE = "один";
    private static final String TWO = "два";
    private static final String THREE = "три";


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /*@Test
    public void testUI() throws IOException {
        File pull = folder.newFile("pull2.txt");
        File target = folder.newFile("pull.txt");
        try (PrintWriter out = new PrintWriter(pull)) {
            out.println(ONE);
        }
        Input input = new StabInput(new String[]{"привет", "стоп", "как дела", "продолжить", "закончить"});
        StartUI startUI = new StartUI(input, new Validator(new String[]{"-d", "pull2.txt", "-o", "output.txt"}));
        StringBuilder rsl = new StringBuilder();
        startUI.init();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        String expected = String.format(
                "привет%n%sстоп%nкакдела%nпродолжить%n%sзакончить",
                ONE, ONE
        );
        assertThat(rsl, is(expected));
    }*/
}
