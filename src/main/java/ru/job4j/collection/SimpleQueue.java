package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        SimpleStack<T> saver = out;
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        T rsl = in.pop();
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return rsl;
    }

    public void push(T value) {
        out.push(value);
    }
}
