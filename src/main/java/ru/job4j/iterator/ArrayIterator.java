package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public ArrayIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    public static class BackwardArrayIterator implements Iterator<Integer> {
        private final int[] data;
        private int point;

        public BackwardArrayIterator(int[] data) {
            this.data = data;
            this.point = data.length - 1;
        }

        @Override
        public boolean hasNext() {
            return point >= 0;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[point--];
        }
    }
}