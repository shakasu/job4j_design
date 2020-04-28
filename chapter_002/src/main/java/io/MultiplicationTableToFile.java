package io;

import java.io.FileOutputStream;

public class MultiplicationTableToFile {

    private static String multiTable(int a) {
        StringBuilder result = new StringBuilder();
        for (int j = 1; j <= a; j++) {
            for (int i = 1; i <= a; i++) {
                result.append(String.format("%3d ", (i * j)));
            }
            result.append(String.format("%n"));
        }
        return result.toString();
    }



    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("MultiplicationTable.txt")) {
            out.write(MultiplicationTableToFile.multiTable(11).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
