package ru.job4j.cache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.cache.AppCache;
import ru.job4j.cache.Writer;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AppCacheTest {
    @Rule
    public TemporaryFolder systemPath = new TemporaryFolder();

    @Rule
    public TemporaryFolder cacheFolder = new TemporaryFolder();


    @Before
    public void setUp() throws IOException {
        File file = systemPath.newFile("file1.txt");
        Writer writer = new Writer(file.getPath());
        writer.record("11");
        writer.record("22");
        writer.record("33");
    }

    @Test
    public void getTest() throws IOException {
        AppCache appCache = new AppCache(systemPath.getRoot().getPath(), cacheFolder.getRoot().getPath());
        assertThat(appCache.get("file1.txt"), is("11 22 33 "));
    }
}
