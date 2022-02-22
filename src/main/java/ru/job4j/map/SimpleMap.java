package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hs = hash(key.hashCode());
        int bucket = indexFor(hs);
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = table[bucket] == null;
        if (rsl) {
            MapEntry<K, V> newValueAndKey = new MapEntry<>(key, value);
            table[bucket] = newValueAndKey;
            modCount++;
            count++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
    }

    @Override
    public V get(K key) {
        int hs = key.hashCode();
        int bucket = indexFor(hs);
        return table[bucket] == null ? null : table[bucket].value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (get(key) != null) {
            table[indexFor(key.hashCode())].key = null;
            table[indexFor(key.hashCode())].value = null;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int bucket = 0;

            @Override
            public boolean hasNext() {
                return bucket < count;
            }

            @Override
            public K next() {
                if (!iterator().hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[bucket++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}