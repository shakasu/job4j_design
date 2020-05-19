package ru.job4j.io.archive;

import ru.job4j.io.base.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    /**
     * Метод перебирает все файлы в директории и исключает файлы нужного формата по предикату.
     * Возвращает лист всех файлов без исключенных.
     * @param directory - директория, по которой пройдет метод.
     * @param exclude - формат файлов, которые исключит метод.
     * @return - лист готовый к архивированию, те без исключаемых файлов.
     * @throws IOException - при невалидных входящих параметрах, валидация определена в
     * @see ArgZip
     */
    public List<Path> exclude(String directory, String exclude) throws IOException {
        Path directoryPath = Path.of(directory);
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(exclude));
        Files.walkFileTree(directoryPath, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод архивирования листа файлов.
     * @param sources - архивируемый лист файлов.
     * @param target - путь к zip - архиву.
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("___Success!___");
    }

    /**
     * Метод архивирования одного файла.
     * @param source - архивируемый файл.
     * @param target - путь к zip - архиву.
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * параметры запуска:
     * @param args -d C:\projects -e .java -o project.zip
     * @throws IOException - при невалидных параметрах.
     */
    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        Zip zip = new Zip();
        zip.packFiles(zip.exclude(argZip.directory(), argZip.exclude()), new File(argZip.output()));
    }
}