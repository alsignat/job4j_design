package ru.job4j.io;
import org.junit.Test;
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

}