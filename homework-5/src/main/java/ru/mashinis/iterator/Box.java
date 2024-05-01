package ru.mashinis.iterator;

import java.util.List;

public class Box<T> {
    private List<T> listOne;
    private List<T> listTwo;
    private List<T> listThree;
    private List<T> listFour;

    public Box(List<T> listOne, List<T> listTwo, List<T> listThree, List<T> listFour) {
            this.listOne = listOne;
            this.listTwo = listTwo;
            this.listThree = listThree;
            this.listFour = listFour;
    }

    public List<T> getListOne() {
        return listOne;
    }

    public List<T> getListTwo() {
        return listTwo;
    }

    public List<T> getListThree() {
        return listThree;
    }

    public List<T> getListFour() {
        return listFour;
    }
}