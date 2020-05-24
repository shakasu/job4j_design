package ru.job4j.io.searcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.searcher.logic.Validator;
import ru.job4j.searcher.start.Finder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты на метод init() в трех различных вариантах.
 */
public class FinderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void initMask() throws IOException {
        //создание двух подпапок, которые будут в основной
        File resultFile = folder.newFile("fileResult.txt");
        File folder2 = folder.newFolder("folder2");
        File folder3 = folder.newFolder("folder3");
        //первый файл в основной
        File file1 = folder.newFile("1file.txt");
        //остальные два файла в двух подпапках
        File file2 = new File(folder2.toString(), "2file.txt");
        File file3 = new File(folder3.toString(), "2file.mp3");
        file2.createNewFile();
        file3.createNewFile();
        String[] args = new String[]{"-d", folder.getRoot().getAbsolutePath(), "-n", "file.txt", "-m", "-o", resultFile.getAbsolutePath()};
        //вызываем init()
        Finder finder = new Finder(new Validator(args));
        finder.init();
        //сравнение
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(resultFile))) {
            in.lines().forEach(result::add);
        }
        List<String> expected = List.of(file1.getName(), file2.getName());
        assertThat(result, is(expected));
    }

    @Test
    public void initFull() throws IOException {
        //создание двух подпапок, которые будут в основной
        File resultFile = folder.newFile("fileResult.txt");
        File folder2 = folder.newFolder("folder2");
        File folder3 = folder.newFolder("folder3");
        //первый файл в основной
        File file1 = folder.newFile("1file.txt");
        //остальные два файла в двух подпапках
        File file2 = new File(folder2.toString(), "2file.txt");
        File file3 = new File(folder3.toString(), "3file.mp3");
        file2.createNewFile();
        file3.createNewFile();
        String[] args = new String[]{"-d", folder.getRoot().getAbsolutePath(), "-n", "2file.txt", "-f", "-o", resultFile.getAbsolutePath()};
        //вызываем init()
        Finder finder = new Finder(new Validator(args));
        finder.init();
        //сравнение
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(resultFile))) {
            in.lines().forEach(result::add);
        }
        List<String> expected = List.of(file2.getName());
        assertThat(result, is(expected));
    }

    @Test
    public void initRegex() throws IOException {
        //создание двух подпапок, которые будут в основной
        File resultFile = folder.newFile("fileResult.txt");
        File folder2 = folder.newFolder("folder2");
        File folder3 = folder.newFolder("folder3");
        //первый файл в основной
        File file1 = folder.newFile("1file.txt");
        //остальные два файла в двух подпапках
        File file2 = new File(folder2.toString(), "2file.txt");
        File file3 = new File(folder3.toString(), "3file.mp3");
        File file4 = new File(folder3.toString(), "44file.txt");
        file2.createNewFile();
        file3.createNewFile();
        String regex = "^\\dfile.\\w{3}";
        String[] args = new String[]{"-d", folder.getRoot().getAbsolutePath(), "-n", regex, "-r", "-o", resultFile.getAbsolutePath()};
        //вызываем init()
        Finder finder = new Finder(new Validator(args));
        finder.init();
        //сравнение
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(resultFile))) {
            in.lines().forEach(result::add);
        }
        List<String> expected = List.of(file1.getName(), file2.getName(), file3.getName());
        assertThat(result, is(expected));
    }
}
