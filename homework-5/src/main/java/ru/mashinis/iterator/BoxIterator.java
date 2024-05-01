package ru.mashinis.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BoxIterator<T> implements Iterator<T> {
    private final Box<T> box;
    private List<T> listAll;
    private int currentIndex;

    public BoxIterator(Box<T> box) {
        this.box = box;
        this.currentIndex = 0;
        this.listAll = new ArrayList<>();
        allList();
    }

    private void allList() {
        listAll.addAll(box.getListOne());
        listAll.addAll(box.getListTwo());
        listAll.addAll(box.getListThree());
        listAll.addAll(box.getListFour());
    }

    @Override
    public boolean hasNext() {
        return currentIndex < listAll.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return listAll.get(currentIndex++);
    }
}