package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("1, 2, 3, 4, 5".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2, 4, 6, 8, 10".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("3, 6, 9, 12, 15".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("4, 8, 12, 16, 20".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("5, 10, 15, 20, 25".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
