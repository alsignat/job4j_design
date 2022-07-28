package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /**
     * takes the list of the files (as path objects) and zips them to the zip archive with given name and hierarchy
     * @param sources list of paths to files to be added in zip archive
     * @param target path to the output archive file
     * @param root folder used to relativize folder structure in zip archive
     */
    public void packFiles(List<Path> sources, Path target, Path root) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(root.relativize(source).toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toAbsolutePath().toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validate(String[] args) {
        ArgsName inputArgs = ArgsName.of(args);
        if (inputArgs.get("d") == null || inputArgs.get("e") == null || inputArgs.get("o") == null) {
            throw new IllegalArgumentException("Correct input: -d=path -e=extensionToExclude -o=outputFile");
        }
        if (!inputArgs.get("e").matches("/\\.[^.]*$/")) {
            throw new IllegalArgumentException("Incorrect file extension: must start with dot");
        }
        if (!inputArgs.get("o").toLowerCase().endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect output file: must have .zip extension.");
        }
        if (!Files.isDirectory(Path.of(inputArgs.get("d")))) {
            throw new IllegalArgumentException("Error. Directory does not exist.");
        }
        return inputArgs;
    }

    public static void main(String[] args) throws IOException {
        ArgsName inputArgs = validate(args);
        Path folder = Path.of(inputArgs.get("d"));
        String extension = inputArgs.get("e");
        Path outputFile = Path.of(inputArgs.get("o"));
        List<Path> filesForArchivation = Search.search(folder, p -> !p.toFile().getName().endsWith(extension));
        Zip zip = new Zip();
        zip.packFiles(filesForArchivation, outputFile, folder);
    }
}
