package ru.mashinis.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoxIterator<T> implements Iterator<T> {
    private final Box<T> box;
    private Iterator<T> iteratorOne;
    private Iterator<T> iteratorTwo;
    private Iterator<T> iteratorThree;
    private Iterator<T> iteratorFour;

    public BoxIterator(Box<T> box) {
        this.box = box;
        this.iteratorOne = box.getListOne().iterator();
        this.iteratorTwo = box.getListTwo().iterator();
        this.iteratorThree = box.getListThree().iterator();
        this.iteratorFour = box.getListFour().iterator();
    }

    @Override
    public boolean hasNext() {
        return iteratorOne.hasNext() || iteratorTwo.hasNext() || iteratorThree.hasNext() || iteratorFour.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (iteratorOne.hasNext()) {
            return iteratorOne.next();
        }
        if (iteratorTwo.hasNext()) {
            return iteratorTwo.next();
        }
        if (iteratorThree.hasNext()) {
            return iteratorThree.next();
        }
        return iteratorFour.next();
    }
}
