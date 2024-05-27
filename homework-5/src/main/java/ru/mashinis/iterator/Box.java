package ru.mashinis.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<T> {
    private List<T> listOne;
    private List<T> listTwo;
    private List<T> listThree;
    private List<T> listFour;

    public Box(List<T> listOne, List<T> listTwo, List<T> listThree, List<T> listFour) {
        if (listOne == null) {
            throw new IllegalArgumentException("listOne cannot be null");
        }
        if (listTwo == null) {
            throw new IllegalArgumentException("listTwo cannot be null");
        }
        if (listThree == null) {
            throw new IllegalArgumentException("listThree cannot be null");
        }
        if (listFour == null) {
            throw new IllegalArgumentException("listFour cannot be null");
        }

        this.listOne = new ArrayList<>(listOne);
        this.listTwo = new ArrayList<>(listTwo);
        this.listThree = new ArrayList<>(listThree);
        this.listFour = new ArrayList<>(listFour);
    }

    public List<T> getListOne() {
        return Collections.unmodifiableList(listOne);
    }

    public List<T> getListTwo() {
        return Collections.unmodifiableList(listTwo);
    }

    public List<T> getListThree() {
        return Collections.unmodifiableList(listThree);
    }

    public List<T> getListFour() {
        return Collections.unmodifiableList(listFour);
    }
}
