package ru.job4j.io.searcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.searcher.io.Writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WriterTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * тест на основной метод класса Writer record().
     * @throws IOException - при io ошибке.
     */
    @Test
    public void record() throws IOException {
        File file = folder.newFile("file1.txt");
        Writer writer = new Writer(file.getPath());
        writer.record("qwe");
        writer.record("asd");
        writer.record("123");
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(result::append);
        }
        String expected = "qweasd123";
        assertThat(result.toString(), is(expected));
    }
}
