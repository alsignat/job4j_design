package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(ln -> !ln.trim().startsWith("#") && !ln.isBlank())
                        .forEach(ln -> {
                        String[] pair = ln.split("=", 2);
                        values.put(pair[0], pair[1]);
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return new HashMap<>(values);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }


    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
