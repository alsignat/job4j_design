package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements ListLinked<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    private class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    /**
     * adds provided values to the end of the list
     * @param value added value
     */
    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value);
        if (size == 0) {
            first = node;
        } else {
            node.setLeft(last);
            last.setRight(node);
        }
        last = node;
        size++;
        modCount++;
    }

    /**
     * returns element with the given index
     * @param index to be searched for
     * @return value of the element with given index
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        boolean reverseDirection = index >= size / 2;
        Node<E> node = reverseDirection ? last : first;
        if (!reverseDirection) {
            for (int i = 0; i < index; i++) {
                node = node.getRight();
            }
        } else {
            for (int i = size - 1; i > index; i--) {
                node = node.getLeft();
            }
        }
        return node.getValue();
    }

    /**
     * creates standard one way iterator
     * @return iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;

            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != last;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.getValue();
                current = current.getRight();
                return value;
            }
        };
    }
}