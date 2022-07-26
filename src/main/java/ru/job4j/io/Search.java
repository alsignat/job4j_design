package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        String[] input = validate(args);
        Path start = Paths.get(input[0]);
        search(start, p -> p.toFile().getName().endsWith(input[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static String[] validate(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of args, valid input: -path -extension");
        }
        try {
            Path start = Paths.get(".");
            if (!Files.isDirectory(start)) {
                throw new InvalidPathException(start.toString(), "Not a directory");
            }
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Incorrect [path] argument: valid input: -path -extension");
        }
        String extension = args[1];
        if (extension.isEmpty() || extension.isBlank()
                || !extension.startsWith(".") || extension.substring(1).contains(".")) {
            throw new IllegalArgumentException("Incorrect [extension] argument: valid input: -path -extension");
        }
        return args;
    }

}
