package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        if (this.data.hasNext()) {
            cursor = data.next();
        }
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (!result) {
            if (cursor.hasNext()) {
                result = true;
            } else if (data.hasNext()) {
                cursor = data.next();
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}