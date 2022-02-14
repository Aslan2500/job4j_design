package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.function.Predicate;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(1);

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next == null) {
                if (value == null) {
                    rsl = false;
                    break;
                }
            } else if (next.equals(value)) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next == null || value == null) {
                if (value == null) {
                    rsl = true;
                    break;
                }
            } else if (next.equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}