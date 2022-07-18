package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> filtered = List.of();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            filtered = in.lines()
                    .filter(l -> l.contains(" 404 "))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        logFilter.save(log, "404.txt");
    }
}
