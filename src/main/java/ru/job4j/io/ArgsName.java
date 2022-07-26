package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] entry = validate(arg);
            values.put(entry[0], entry[1]);
        }
    }

    private String[] validate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("Invalid input. Correct input: -key=value");
        }
        String[] entry = arg.split("=", 2);
        if (entry.length < 2 || entry[0].length() < 2
                || entry[0].isBlank() || entry[1].isBlank()) {
            throw new IllegalArgumentException("Invalid input. Correct input: -key=value");
        }
        return new String[] {entry[0].substring(1), entry[1]};
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
