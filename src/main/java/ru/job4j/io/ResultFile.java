package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        int size = 10;
        ResultFile rsl = new ResultFile();
        rsl.resultMatrix(size);
    }

    public void resultMatrix(int size) {
        int[][] matrix = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j == 0) {
                        matrix[i][j] = (i + 1) * (j + 1);
                        out.write(("" + matrix[i][j]).getBytes());
                    } else {
                        matrix[i][j] = (i + 1) * (j + 1);
                        out.write((", " + matrix[i][j]).getBytes());
                    }
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
