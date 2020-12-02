package ru.job4j.design.isp;

import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };
    private static final String LN = System.lineSeparator();
    private final String menu = String.format("0. Add new item.%s%s1. Edit item.%s%s2. Delete item.%s%s3. Find by Id.%s%s4. Exit%s%s", LN, LN, LN, LN, LN, LN, LN, LN, LN, LN, LN, LN);

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

//    @Test
//    public void whenShowMenu() {
//        ValidateInput validateInput =  null; //new StubInput(new String[]{"4"});
//        new StartUI(validateInput, output).init();
//        assertThat(new String(this.out.toByteArray()), is(menu));
//    }
//
//    @Test
//    public void createActionTest() {
//        ValidateInput validateInput = null; //new StubInput(new String[]{"0, 4"});
//        new StartUI(validateInput, output).init();
//        String rsl = String.format("%s%sexecuting create action%s%s", menu, LN, LN, menu);
//        assertThat(new String(this.out.toByteArray()), is(rsl));
//    }
}
