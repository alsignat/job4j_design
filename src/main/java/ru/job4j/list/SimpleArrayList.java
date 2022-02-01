package ru.job4j.list;
import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    /**
     * creates empty list with given capacity
     * @param capacity initial capacity
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * adds element to the SimpleArrayList container; if list is overflown, doubles list capacity prior to adding
     * @param value value to add
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, grow());
        }
        container[size] = value;
        size++;
        modCount++;
    }

    private int grow() {
        return size == 0 ? 10 : size * 2;
    }

    /**
     * places provided value on specified position and returns old element value
     * @param index destination index
     * @param newValue value to be placed
     * @return replaced value
     * @throws IndexOutOfBoundsException if index is not in bound of list size (from 0 to size exclusive)
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    /**
     * deletes and returns element on given position in the list
     * @param index destination index
     * @return removed value
     * @throws IndexOutOfBoundsException if index is not in bound of list size (from 0 to size exclusive)
     */
    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(container, index + 1, container, index, size - (index + 1));
        container[--size] = null;
        modCount++;
        return value;
    }

    /**
     * returns element on given position in the list
     * @param index index of element to be returned
     * @return element on specified index position
     * @throws IndexOutOfBoundsException if index is not in bound of list size (from 0 to size exclusive)
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * returns size (the number of elements in the list)
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns standard iterator for one-way iteration over the list
     * @return Iterator<T> object to iterate over the list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final int expectedModCount = modCount;
            private int pointer;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }
}