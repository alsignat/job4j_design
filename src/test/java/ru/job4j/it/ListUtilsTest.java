package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertEquals(input, Arrays.asList(1, 2, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void whenAddInTheMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 1, 4);
        assertEquals(input, Arrays.asList(0, 1, 4, 2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenAddWithIncorrectIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 3, 3);
        assertEquals(input, Arrays.asList(0, 1, 2, 3));
    }

    @Test
    public void removeIfEvenMiddleSingle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 5));
        ListUtils.removeIf(input, num -> num % 2 == 0);
        assertEquals(input, Arrays.asList(1, 5));
    }

    @Test
    public void removeIfEvenLastMultiple() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 6, 8));
        ListUtils.removeIf(input, num -> num % 2 == 0);
        assertEquals(input, Arrays.asList(1, 1));
    }

    @Test
    public void replaceIfEvenMiddleSingle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 5));
        ListUtils.replaceIf(input, num -> num % 2 == 0, 101);
        assertEquals(input, Arrays.asList(1, 101, 5));
    }

    @Test
    public void replaceIfEvenLastMultiple() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 6, 8));
        ListUtils.replaceIf(input, num -> num % 2 == 0, 101);
        assertEquals(input, Arrays.asList(1, 1, 101, 101));
    }

    @Test
    public void removeAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 6, 8, 5, 10));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 6, 10, 9));
        ListUtils.removeAll(input, elements);
        assertEquals(input, Arrays.asList(8, 5));
    }



}