package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size = 0;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void expand(T[] arr) {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length - 1) {
            expand(container);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = container[Objects.checkIndex(index, size)];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = container[Objects.checkIndex(index, size)];
        modCount++;
        System.arraycopy(container,
                index + 1,
                container, index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                while (index < size && container[index] == null) {
                    index++;
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!iterator().hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }

        };
    }
}
