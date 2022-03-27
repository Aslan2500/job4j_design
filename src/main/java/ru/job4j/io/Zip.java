package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough parameters");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        ArgsName name = ArgsName.of(args);
        if (!name.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Inappropriate format of files for exception");
        }
        Path directory = Path.of(name.get("d"));
        String exclude = name.get("e");
        List<Path> list = Search.search(directory, path -> !path.toFile().getName().endsWith(exclude));
        File out = new File(name.get("o"));
        Zip zip = new Zip();
        zip.packFiles(list, out);
    }
}
