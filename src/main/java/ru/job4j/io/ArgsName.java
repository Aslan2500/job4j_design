package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.contains("=")) {
                continue;
            }
            String[] rsl = s.split("=", 2);
            if (rsl[0].isEmpty() || rsl[1].isEmpty() || !rsl[0].startsWith("-")) {
                throw new IllegalArgumentException();
            }
            rsl[0] = rsl[0].substring(1);
            if (rsl[0].isEmpty()) {
                throw new IllegalArgumentException();
            }
            values.put(rsl[0], rsl[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
