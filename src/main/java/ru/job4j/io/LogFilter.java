package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines().filter(s -> {
                String[] words = s.split(" ");
                return "404".equals(words[words.length - 2]);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            if (log != null) {
                for (String s : log) {
                    out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
