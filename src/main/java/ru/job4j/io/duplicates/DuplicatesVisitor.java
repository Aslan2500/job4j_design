package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (files.containsKey(fileProperty)) {
            files.get(fileProperty).add(file);
        } else {
            List<Path> list = new ArrayList<>();
            list.add(file);
            files.put(fileProperty, list);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<List<Path>> finderDuplicate() {
        return files.values().stream()
                .filter(paths -> paths.size() > 1)
                .collect(Collectors.toList());
    }

    public void listOfDuplicates(String catalog) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(catalog), duplicatesVisitor);
        List<List<Path>> rsl = duplicatesVisitor.finderDuplicate();
        for (List<Path> list : rsl) {
            for (Path res : list) {
                System.out.println(res);
            }
        }
    }
}
