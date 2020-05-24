package ru.job4j.io.searcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.searcher.logic.Validator;

import java.io.File;
import java.io.IOException;

/**
 * Тесты на то, что невалидные параметры не пройдут валидацию.
 */
public class ValiadatorTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void wrongSyntax() {
        String[] syntax = new String[]{"adsfasdf", "asdasd", "w2342e", "adsfasdf", "asdasd", "w2342e", "adsfasdf", "asdasd", "w2342e"};
        Validator validator = new Validator(syntax);
        validator.directory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void notDirectoryInput() throws IOException {
        File file = folder.newFile("txt.txt");
        String[] notDir = new String[]{"-d", file.toString(), "-n", ".txt", "-m", "-o", " "};
        Validator validator = new Validator(notDir);
        validator.directory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void notTxtOutput() throws IOException {
        File file = folder.newFile("txt.sdasd");
        String[] notTxt = new String[]{"-d", folder.toString(), "-n", ".txt", "-m", "-o", file.toString()};
        Validator validator = new Validator(notTxt);
        validator.directory();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonexistentType() throws IOException {
        File file = folder.newFile("txt.txt");
        String[] notType = new String[]{"-d", folder.toString(), "-n", ".txt", "-z", "-o", file.toString()};
        Validator validator = new Validator(notType);
        validator.directory();
    }
}
