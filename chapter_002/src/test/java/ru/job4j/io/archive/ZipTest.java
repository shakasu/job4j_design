package ru.job4j.io.archive;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ZipTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void excludeTest() throws IOException {
        File file1 = folder.newFile("file1.txt");
        File file2 = folder.newFile("file2.java");
        File file3 = folder.newFile("file3.txt");
        File file4 = folder.newFile("file4.txt");
        Zip zip = new Zip();
        String directory = folder.getRoot().getAbsolutePath();
        List<Path> result = zip.exclude(directory, ".java");
        List<Path> expected = List.of(file1.toPath(), file3.toPath(), file4.toPath());
        assertThat(result, is(expected));
    }

    @Test
    public void archiveTest() throws IOException {
        //файлы, которые будут запакованы
        //подпапка нужна, чтобы архив не архивировал себя
        File subFolder = folder.newFolder("files");
        File file1 = folder.newFile("files/file1.txt");
        File file2 = folder.newFile("files/file2.java");
        File file3 = folder.newFile("files/file3.txt");
        File file4 = folder.newFile("files/file4.txt");
        Zip zip = new Zip();
        //путь, где хранятся эти 4 файла
        String directory = folder.getRoot().getAbsolutePath();
        //архив в другой временной папке
        File resultPack = folder.newFile("result.zip");
        //помещение файлов в архив
        zip.packFiles(zip.exclude(directory, ".java"), resultPack);
        //ожидаемых список имен
        List<String> expected = List.of(file1.getCanonicalPath(), file3.getCanonicalPath(), file4.getCanonicalPath());
        //чтение имен файлов из заданного архива
        List<String> result = zip.readSinglePack(resultPack);
        assertThat(result, is(expected));
    }
}
