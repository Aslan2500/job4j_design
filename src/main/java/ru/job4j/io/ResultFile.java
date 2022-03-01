package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        int[][] matrix = multiple(10);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] ints : matrix) {
                for (int j = 0; j < ints.length; j++) {
                    if (j == 0) {
                        System.out.print("" + ints[j]);
                    } else {
                        System.out.print(", " + ints[j]);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}
