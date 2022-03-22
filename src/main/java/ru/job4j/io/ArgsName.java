package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("There is no such key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            validate(s);
            String[] rsl = s.split("=", 2);
            rsl[0] = rsl[0].substring(1);
            values.put(rsl[0], rsl[1]);
        }
    }

    public static void validate(String s) {
        if (!s.startsWith("-") || !s.contains("=")
                || s.startsWith("-=") || s.indexOf("=") == s.length() - 1) {
            throw new IllegalArgumentException("Pattern violation");
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
