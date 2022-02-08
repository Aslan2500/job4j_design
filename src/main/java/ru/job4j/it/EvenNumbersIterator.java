package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;
        for (; index < data.length; index++) {
            if (data[index] % 2 == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = index;
        index++;
        return data[i];
    }
}
