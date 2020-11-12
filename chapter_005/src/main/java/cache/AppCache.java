package cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class AppCache {
    private final HashMapCache<String, String> cache;
    private final Path systemPath;
    private final Path cacheFolder;

    /**
     * Constructor create two paths for correct work.
     * @param systemPath - folder with files.
     * @param cacheFolder - folder for cache.
     */
    public AppCache(String systemPath, String cacheFolder) {
        this.cache = new HashMapCache<>();
        this.systemPath = Paths.get(systemPath);
        this.cacheFolder = Paths.get(cacheFolder);
        if (!(
                        Files.isDirectory(this.systemPath)
                                || Files.isDirectory(this.cacheFolder)
                )) {
            throw new IllegalArgumentException("path not found");
        }
    }

    /**
     * Method gets file from cache,
     * if file does not exist, its reload current cache.
     * @param name - key.
     * @return - content into String format.
     * @throws IOException
     */
    public String get(String name) throws IOException {
        if (!cache.containsKey(name)) {
            load();
        }
        return cache.get(name);
    }

    /**
     * Method loads *.txt files`s paths into list,
     * and insert it in cache map.
     * @throws IOException
     */
    private void load() throws IOException {
        List<Path> files = Files.list(systemPath).filter(p -> p.toString().endsWith(".txt")).collect(Collectors.toList());
        Files.copy(systemPath, cacheFolder, StandardCopyOption.REPLACE_EXISTING);
        for (Path file : files) {
            cache.put(file.getFileName().toString(), read(file));
        }
    }

    /**
     * Method read *.txt by lines and transform it into String.
     * @param path - path of *.txt file
     * @return - content into String format.
     * @throws IOException
     */
    private String read(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        StringBuilder text = new StringBuilder();
        for (String line: lines) {
            text.append(line);
            text.append(" ");
        }
        return text.toString();
    }
}
