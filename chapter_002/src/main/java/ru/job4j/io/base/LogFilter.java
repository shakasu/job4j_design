package ru.job4j.io.base;

import java.io.*;
import java.util.*;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(line -> Arrays.asList(line.split(" ")).contains("404")).forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                )
        )) {
            String logStr = "";
            for (String line : log) {
                logStr = String.format("%s%n%s", logStr, line);
            }
            out.write(logStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
        save(log, "logs_with_404");
    }
}
