package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> last = null;

    private Node<E> first = null;

    private int modCount = 0;

    @Override
    public void add(E value) {
        if (last == null) {
            Node<E> nodeOne = new Node<>(value, null, null);
            this.last = nodeOne;
            this.first = nodeOne;
            modCount++;
            return;
        }
        Node<E> newNode = new Node<>(value, null, last);
        last.setNext(newNode);
        this.last = newNode;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, modCount);
        Node<E> testNode = first;
        E rsl = first.getElement();
        for (int i = 0; i < index; i++) {
            rsl = testNode.getNext().getElement();
            testNode = testNode.getNext();
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < modCount;
            }

            @Override
            public E next() {
                if (!iterator().hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(index++);
            }
        };
    }

}
