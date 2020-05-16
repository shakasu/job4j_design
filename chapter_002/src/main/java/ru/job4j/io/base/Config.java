package ru.job4j.io.base;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс Config читает текстовый файл и вносит в карту пары ключа-значения,
 * разделяя строку знаком "=".
 */
public class Config {

    /**
     * путь файла.
     */
    private final String path;

    /**
     * карта, в которую вносятся пары.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Конструктор принимает путь файла.
     * @param path - путь.
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод осуществляет чтение из файла и запись в карту.
     * Так как, в условиях карта values - final, то объявлена промежуточная карта result.
     * В нее вносятся пары по следующим правилам:
     * Если строка начитается с # - она является комментарием,
     * Если строка не содержит "=" или начинается с этого оператора - она не интересна для записи в карту,
     * Если ключ (до знака =) содержит # - значит значение закомментировано и недоступно для записи.
     * Если значение начинается с # - значит оно закомментировано и недоступно для записи.
     * После данного фильтра, собирается карта по следующим правилам:
     * Ключом является все до знака =, значением - все, что между = и #.
     * После всего копируем пары из промежуточной карты в values.
     */
    public void load() {
        Map<String, String> result;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            result = read
                    .lines()
                    .filter(line -> !line.startsWith("#")
                            && line.contains("=")
                            && !line.startsWith("=")
                            && !line.split("=")[0].contains("#")
                            && !line.split("=")[1].startsWith("#")
                    )
                    .collect(
                            Collectors.toMap(
                                    key -> key.split("=")[0],
                                    value -> value.split("=")[1].split("#")[0]
                            )
                    );
            values.putAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getter для значения карты.
     * @param key - ключ элемента.
     * @return - значение, соответствующее ключу.
     */
    public String value(String key) {
        return values.get(key);
    }
}