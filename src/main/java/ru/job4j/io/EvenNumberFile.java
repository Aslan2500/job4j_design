package ru.job4j.io;

import java.io.FileInputStream;

import static java.lang.System.out;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] arr = text.toString().split(System.lineSeparator());
            int[] rsl = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                rsl[i] = Integer.parseInt(arr[i]);
            }
            StringBuilder result = new StringBuilder();
            for (int i : rsl) {
                if (i % 2 == 0) {
                    result.append(i).append(" - чётное\n");
                } else {
                    result.append(i).append(" - не чётное\n");
                }
            }
            out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}