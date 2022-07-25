package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final HashMap<FileProperty, ArrayList<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            FileProperty properties = new FileProperty(attrs.size(), file.getFileName().toString());
            if (!files.containsKey(properties)) {
                files.put(properties, new ArrayList<>(List.of(file)));
            } else {
                List<Path> found = files.get(properties);
                if (found.size() == 1) {
                    System.out.println(found.get(0));
                }
                System.out.println(file);
            }
        }
        return super.visitFile(file, attrs);
    }
}
