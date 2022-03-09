package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            List<String> list = read.lines()
                    .filter(Objects::nonNull)
                    .filter(s -> !s.startsWith("#"))
                    .filter(l -> l.length() > 0)
                    .filter(a -> {
                        boolean rsl = true;
                        if (a.startsWith("=") || a.endsWith("=")) {
                            throw new IllegalArgumentException();
                        }
                        return rsl;
                    })
                    .collect(Collectors.toList());
            for (String s : list) {
                if (!s.contains("=")) {
                    throw new IllegalArgumentException();
                }
                String[] res = s.split("=", 2);
                values.put(res[0], res[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String s = null;
        if (values.containsKey(key)) {
            s = values.get(key);
        }
        return s;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}
