package ru.mashinis.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static int count = 0;

    public static void main(String[] args) {

        Box<String> box = new Box<>(addTestData(), addTestData2(), addTestData(), addTestData());
        print(new BoxIterator<>(box));
    }

    private static void print(Iterator<String> it) {
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }

    private static List<String> addTestData() {
        ++count;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("List " + count + " - Item " + i);
        }
        list.add("------------------");

        return list;
    }

    private static List<String> addTestData2() {
        ++count;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("List " + count + " - Item " + i);
        }
        list.add("------------------");

        return list;
    }
}