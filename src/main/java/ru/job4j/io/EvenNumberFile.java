package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            int[] numbers = Arrays.stream(text.toString().split("\r?\n|\r"))
                    .mapToInt(Integer::parseInt)
                    .filter(n -> n % 2 == 0)
                    .toArray();
            System.out.println(Arrays.toString(numbers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
