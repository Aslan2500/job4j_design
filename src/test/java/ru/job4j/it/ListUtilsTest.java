package ru.job4j.it;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
        assertThat(input, is(100));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfDividedBy2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9));
        Predicate<Integer> filter = s -> s % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(1, 3, 7, 9)));
    }

    @Test
    public void whenReplaceIfDividedBy3() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 9, 6, 18, 4, 33));
        Predicate<Integer> filter = s -> s % 3 == 0;
        ListUtils.replaceIf(input, filter, 0);
        assertThat(input, is(Arrays.asList(1, 2, 0, 0, 0, 0, 4, 0)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4, 6));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(1, 3, 5)));
    }
}