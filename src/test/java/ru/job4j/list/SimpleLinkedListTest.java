package ru.job4j.list;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(list.get(0).intValue(), 1);
        assertEquals(list.get(1).intValue(), 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenAddIterHasNextTrue() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIterNextOne() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertEquals(it.next().intValue(), 1);
    }

    @Test
    public void whenEmptyIterHashNextFalse() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenAddIterMultiHasNextTrue() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenAddIterNextOneNextTwo() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertEquals(it.next().intValue(), 1);
        assertEquals(it.next().intValue(), 2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> first = list.iterator();
        assertTrue(first.hasNext());
        assertEquals(first.next().intValue(), 1);
        assertTrue(first.hasNext());
        assertEquals(first.next().intValue(), 2);
        assertFalse(first.hasNext());
        Iterator<Integer> second = list.iterator();
        assertTrue(second.hasNext());
        assertEquals(second.next().intValue(), 1);
        assertTrue(second.hasNext());
        assertEquals(second.next().intValue(), 2);
        assertFalse(second.hasNext());
    }

}