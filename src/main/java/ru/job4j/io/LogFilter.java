package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
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


    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}
