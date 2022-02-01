package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertEquals(stack.pop().intValue(), 1);
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertEquals(stack.pop().intValue(), 2);
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals(stack.pop().intValue(), 1);
    }

}