package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public static void main(String[] args) {
        File file = new File(".");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("size : %s%n", file.getTotalSpace());
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("name: %s; size: %s bytes \n", subfile.getName(), subfile.length());
        }
    }

}
