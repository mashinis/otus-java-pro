package ru.mashinis.iterator;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private List<T> listOne;
    private List<T> listTwo;
    private List<T> listThree;
    private List<T> listFour;

    public Box(List<List<T>> list) {
        if (list.size() <= 4) {
            listOne = list.get(0);
            listTwo = list.get(1);
            listThree = list.get(2);
            listFour = list.get(3);
        }

    }

    public List<List<T>> getAllList() {
        List<List<T>> lists = new ArrayList<>();
        lists.add(listOne);
        lists.add(listTwo);
        lists.add(listThree);
        lists.add(listFour);

        return lists;
    }
}

