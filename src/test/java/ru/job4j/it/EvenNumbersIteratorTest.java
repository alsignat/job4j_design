package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class EvenNumbersIteratorTest {


    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 2);
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 6);
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 2);
        assertEquals(it.next().intValue(), 4);
        assertEquals(it.next().intValue(), 6);
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertFalse(it.hasNext());
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6});
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 2);
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next().intValue(), 6);
        assertFalse(it.hasNext());
    }

}