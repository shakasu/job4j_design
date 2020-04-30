package ru.job4j.io;

import io.Config;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
    }

    @Test
    public void whenPairWithCommentBeforeValue1() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hiber"),
                is("")
        );
    }

    @Test
    public void whenPairWithCommentBeforeValue2() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.url"),
                is("")
        );
    }

    @Test
    public void whenPairWithCommentInValue() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.driver_class"),
                is("org")
        );
    }

    @Test
    public void whenPairWithCommentAfterValue() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
    }
}