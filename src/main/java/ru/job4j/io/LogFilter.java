package ru.job4j.io;

import ru.job4j.list.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Predicate;

public class LogFilter {
    public List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(s -> s.contains(" 404 ")).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}