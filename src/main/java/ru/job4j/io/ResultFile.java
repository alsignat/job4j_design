package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] table = Matrix.multiple(9);
            byte[] tableToWrite = Arrays.deepToString(table).getBytes();
            out.write(tableToWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Matrix {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                array[row][col] = (row + 1) * (col + 1);
            }
        }
        return array;
    }
}