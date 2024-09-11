package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    int modCount;
    int expectedModCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        modCount++;
        expectedModCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removable = container[index];
        Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        modCount++;
        size--;
        return removable;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    void grow() {
        T[] newContainer = (T[]) new Object[container.length * 2 + 1];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }


    @Override
    public Iterator<T> iterator() {
        expectedModCount  = modCount;
        return new Iterator<T>() {
            int pointer;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
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