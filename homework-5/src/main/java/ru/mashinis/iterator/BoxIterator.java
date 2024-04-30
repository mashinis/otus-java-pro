package ru.mashinis.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public final class BoxIterator<T> implements Iterator<T> {
    private final Iterator<List<T>> outerIterator;
    private Iterator<T> innerIterator;

    public BoxIterator(Box<T> box) {
        this.outerIterator = box.getAllList().iterator();
        if (outerIterator.hasNext()) {
            this.innerIterator = outerIterator.next().iterator();
        }
    }

    @Override
    public boolean hasNext() {
        while (outerIterator.hasNext() && (innerIterator == null || !innerIterator.hasNext())) {
            innerIterator = outerIterator.next().iterator();
        }
        return innerIterator != null && innerIterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return innerIterator.next();
    }
}

