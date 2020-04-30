package io;

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
     * Так как, в условииях карта values - final, то объявлена промежуточная карта result.
     * В нее вносятся пары по следующийм правилам:
     * Если строка начитается с # - она является комментарием,
     * Если строка не содержит "=" или начинается с этого оператора - она не интересна для записи в карту.
     * После данного фильтра, собирается карта по следующим правилам:
     * ключом является та часть строки, которая находится до "=" и до "#",
     * значением является та часть, которая после "=" и перед "#".
     * После всего копируем пары из промежуточной карты в values.
     */
    public void load() {
        Map<String, String> result;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            result = read
                    .lines()
                    .filter(line -> !line.startsWith("#")
                            && line.contains("=")
                            && !line.startsWith("="))
                    .collect(
                            Collectors.toMap(
                                    key -> key.split("=")[0].contains("#")
                                    ? key.split("=")[0].split("#")[0] : key.split("=")[0],
                                    value -> value.split("=")[1].contains("#")
                                    ? value.split("=")[1].split("#")[0] : value.contains("#")
                                    ? "" : value.split("=")[1]
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