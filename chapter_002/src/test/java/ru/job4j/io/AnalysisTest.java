package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {
    @Test
    public void unavailable() {
        Analysis analysis = new Analysis();
        String source = "./src/main/resources/server.log";
        String target = "./src/main/resources/target.log";
        analysis.unavailable(source, target);

        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            assertThat(read.readLine(), is("10:57:01;10:59:01"));
            assertThat(read.readLine(), is("11:01:02;11:02:02"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
