package ru.job4j.io.duplicates;

import java.io.IOException;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        duplicatesVisitor.listOfDuplicates(".");
    }
}
