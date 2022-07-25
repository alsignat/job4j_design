package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

class AnalyzerTest {

    @Test
    void unavailableLater(@TempDir Path tempDir) throws IOException {
        Analyzer analyzer = new Analyzer();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            String data = """
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    200 10:59:01
                    500 11:01:02
                    200 11:02:02
                    """;
            out.println(data);
        }
        File target  = tempDir.resolve("target.txt").toFile();
        analyzer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().map(ln -> ln + System.lineSeparator()).forEach(rsl::append);
        }
        String expected = """
                10:57:01 10:59:01
                11:01:02 11:02:02
                """.replace("\n", System.lineSeparator());
        Assertions.assertEquals(expected, rsl.toString());
    }

    @Test
    void unavailableAtStart(@TempDir Path tempDir) throws IOException {
        Analyzer analyzer = new Analyzer();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            String data = """
                    400 10:56:01
                    500 10:57:01
                    400 10:58:01
                    200 10:59:01
                    500 11:01:02
                    200 11:02:02
                    """;
            out.println(data);
        }
        File target  = tempDir.resolve("target.txt").toFile();
        analyzer.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().map(ln -> ln + System.lineSeparator()).forEach(rsl::append);
        }
        String expected = """
                10:56:01 10:59:01
                11:01:02 11:02:02
                """.replace("\n", System.lineSeparator());
        Assertions.assertEquals(expected, rsl.toString());
    }
}