package collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Article {

    private static final String REGEX = "[^а-я^А-Яa-zA-Z]";
    private static final String REPLACEMENT = " ";

    private static HashSet<String> set(String str) {
        return new HashSet<>(Arrays.asList(str.replaceAll(REGEX, REPLACEMENT).toLowerCase().split(REPLACEMENT)));
    }

    public static boolean generateBy(String origin, String line) {
        Set<String> result = set(origin);
        int firstSize = result.size();
        result.addAll(set(line));
        int secondSize = result.size();
        return firstSize == secondSize;
    }
}
