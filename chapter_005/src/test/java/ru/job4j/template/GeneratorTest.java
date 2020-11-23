package ru.job4j.template;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

public class GeneratorTest {
    private Generator generator;
    private String template;
    private Map<String, String> args;

    @Before
    public void setUp() {
        generator = new HelloGenerator();
        template = "Hello ${name}, how are ${subject}?";
        args = Map.of("Karen", "you doing",
                "", "you",
                "guys", ""
                );
    }

    @Test
    public void produce() {
        assertThat(
                generator.produce(template, args),
                is("Hello Karen, how are you doing?")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchKeys() {
        assertThat(
                generator.produce(template, args),
                is("Hello, how are you?")
        );
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchValues() {
        assertThat(
                generator.produce(template, args),
                is("Hello guys, how are ?")
        );
    }
}
