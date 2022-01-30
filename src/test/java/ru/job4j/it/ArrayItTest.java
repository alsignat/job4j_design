package ru.job4j.it;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertEquals(it.next().intValue(), 1);
        assertEquals(it.next().intValue(), 2);
        assertEquals(it.next().intValue(), 3);
    }


}