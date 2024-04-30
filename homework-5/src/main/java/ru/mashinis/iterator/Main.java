package ru.mashinis.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Box<String> box = new Box(addTestData());
        print(new BoxIterator<>(box));
    }

    private static void print(Iterator<String> it) {
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }

    private static List<List<String>> addTestData() {
        List<List<String>> lists = new ArrayList<>();
        for (int l = 0; l < 4; l++) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("List " + l + " - Item " + i);
            }
            list.add("------------------");
            lists.add(list);
        }
        return lists;
    }
}
