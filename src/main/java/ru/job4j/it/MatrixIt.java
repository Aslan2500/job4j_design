package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row == data[column].length) {
            column++;
            row = 0;
        }
        if (column < data.length) {
            while (column < data.length - 1 && data[column].length == 0) {
                column++;
            }
            return row < data[column].length;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[column][row++];
    }
}
