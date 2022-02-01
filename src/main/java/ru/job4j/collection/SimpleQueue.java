package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inStack;
    private int outQueue;

    public T poll() {
        if (outQueue == 0) {
            rebalance();
        }
        T el = out.pop();
        outQueue--;
        return el;

    }

    private void rebalance() {
        while (inStack > 0) {
            out.push(in.pop());
            inStack--;
            outQueue++;
        }
    }

    public void push(T value) {
        in.push(value);
        inStack++;
    }
}
