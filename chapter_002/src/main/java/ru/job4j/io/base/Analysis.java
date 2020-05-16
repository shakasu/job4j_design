package ru.job4j.io.base;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    int evenCount = 0;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            List<String> list = new ArrayList<>();
            read.lines().forEach(
                    line -> {
                        if (evenCount % 2 == 0) {
                            if (Integer.parseInt(line.split(" ")[0]) > 350) {
                                list.add(line.split(" ")[1]);
                                evenCount++;
                            }
                        } else {
                            if (Integer.parseInt(line.split(" ")[0]) < 350) {
                                list.add(line.split(" ")[1]);
                                evenCount++;
                            }
                        }
                    }
            );
            for (int i = 0; i < list.size(); i += 2) {
                out.printf("%s;%s%n", list.get(i), list.get(i + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
