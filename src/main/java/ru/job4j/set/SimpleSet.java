package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean notPresent = !contains(value);
        if (notPresent) {
            set.add(value);
        }
        return notPresent;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}