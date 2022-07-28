package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        Scanner scanner = new Scanner(Paths.get(path)).useDelimiter(delimiter);
        Writer output = out.equals("stdout") ? new OutputStreamWriter(System.out) : new FileWriter(out);
        PrintWriter writer = new PrintWriter(new BufferedWriter(output));
        String[] header = scanner.nextLine().split(delimiter);
        List<Integer> filterColumns = new ArrayList<>();
        for (int i = 0; i < filters.length; i++) {
            String filter = filters[i];
            for (int j = 0; j < header.length; j++) {
                String columnName = header[j];
                if (columnName.equals(filter)) {
                    writer.print(filter + (i == filters.length - 1 ? "" : delimiter));
                    filterColumns.add(j);
                }
            }
        }
        writer.println();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(delimiter);
            for (int i = 0; i < filterColumns.size(); i++) {
                int col = filterColumns.get(i);
                writer.print(values[col] + (i == filterColumns.size() - 1 ? "" : delimiter));
            }
            writer.println();
        }
        writer.close();
        scanner.close();
    }

    public static ArgsName validate(ArgsName args) {
        try {
            Path start = Paths.get(args.get("path"));
            if (!Files.isRegularFile(start)) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Input file does not exist.");
        }

        try {
            Path out = Paths.get(args.get("out"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Output file can not be created.");
        }

        if (args.get("delimiter") == null || args.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Delimiter not specified.");
        }

        if (args.get("filter") == null || args.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Filter not specified.");
        }
        return args;
    }

    public static void main(String[] args) throws Exception {
        CSVReader.handle(validate(ArgsName.of(args)));
    }
}
