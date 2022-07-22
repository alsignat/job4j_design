package ru.job4j.io;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr Arsentev", config.value("name"));
    }

    @Test
    public void whenCommentsAndBlanks() {
        String path = "./data/input_with_comments_and_blanks.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(5, config.getValues().size());
        assertEquals("password", config.value("hibernate.connection.password"));
    }

    @Test
    public void whenInvalidItemsSyntax() {
        String path = "./data/input_with_invalid_syntax.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(7, config.getValues().size());
        assertEquals("ivan=1", config.value("name"));
        assertEquals("ivanov=", config.value("surname"));
    }

    @Test
    public void whenMeetsException() {
        String path = "./data/broken_input.txt";
        Config config = new Config(path);
        config.load();
        Long errorsCount = 0L;
        try (BufferedReader logReader = new BufferedReader(new FileReader("./data/errors_log.txt"))) {
            errorsCount = logReader.lines().count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long expected = 4L;
        assertEquals(expected, errorsCount);
    }

}