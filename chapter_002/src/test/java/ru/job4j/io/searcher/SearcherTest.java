package ru.job4j.io.searcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.searcher.logic.Searcher;
import ru.job4j.searcher.logic.Validator;
import ru.job4j.searcher.start.Finder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearcherTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * тест на основной метод класса Searcher find().
     * @throws IOException - при io ошибке.
     */
    @Test
    public void find() throws IOException {
        //создание двух подпапок, которые будут в основной
        File folder2 = folder.newFolder("folder2");
        File folder3 = folder.newFolder("folder3");
        //первый файл в основной
        File file1 = folder.newFile("file1.txt");
        //остальные два файла в двух подпапках
        File file2 = new File(folder2.toString(), "file2.txt");
        File file3 = new File(folder3.toString(), "file3.mp3");
        file2.createNewFile();
        file3.createNewFile();
        Searcher searcher = new Searcher(folder.getRoot().toPath(), p -> p.toFile().getName().endsWith("txt"));
        List<Path> expected = List.of(file1.toPath(), file2.toPath());
        List<Path> result = searcher.find();
        assertThat(result, is(expected));
    }
}
