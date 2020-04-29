package io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(evenCheck(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String evenCheck(StringBuilder read) {
        StringBuilder result = new StringBuilder();
        String[] lines = read.toString().split(System.lineSeparator());
        for (String line : lines) {
            result.append(String.format("%s - is %b even;%n", line, (Integer.parseInt(line) % 2 == 0)));
        }
        return result.toString();
    }

}

