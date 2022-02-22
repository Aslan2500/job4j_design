package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putSameBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "Hello");
        boolean rsl2 = map.put(0, "World");
        assertTrue(rsl);
        assertFalse(rsl2);
    }

    @Test
    public void putDiffBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "Hello,");
        boolean rsl2 = map.put(1, "World!");
        boolean rsl3 = map.put(2, "What's");
        boolean rsl4 = map.put(3, "Up?");
        assertTrue(rsl);
        assertTrue(rsl2);
        assertTrue(rsl3);
        assertTrue(rsl4);
    }

    @Test
    public void get123AndPutInTheSameBucket() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "123");
        boolean rsl1 = map.put(1, "123");
        boolean rsl2 = map.put(1, "1");
        String expected = "123";
        assertThat(map.get(0), is(expected));
        assertThat(map.get(1), is(expected));
    }

    @Test
    public void getNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        boolean rsl = map.put(0, "123");
        String expected = "123";
        assertThat(map.get(0), is(expected));
        assertNull(map.get(1));
    }

    @Test
    public void removeAll() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        map.put(1, "Second");
        map.put(2, "Third");
        assertTrue(map.remove(0));
        assertTrue(map.remove(1));
        assertTrue(map.remove(2));
    }

    @Test
    public void removeFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(0));
        assertFalse(map.remove(3));
        assertFalse(map.remove(123));
    }

    @Test(expected = NoSuchElementException.class)
    public void noElementIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iter = map.iterator();
        iter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        Iterator<Integer> iter = map.iterator();
        map.put(1, "Second");
        iter.next();
    }

    @Test
    public void iteratorHasNext() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(0, "First");
        map.put(1, "Second");
        map.put(2, "Third");
        Iterator<Integer> iter = map.iterator();
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(0));
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(1));
        assertTrue(iter.hasNext());
        assertThat(iter.next(), is(2));
        assertFalse(iter.hasNext());
    }
}