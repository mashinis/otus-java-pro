package ru.mashinis.iterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void testBoxConstructor() {
        List<String> listOne = Arrays.asList("One", "Two", "Three");
        List<String> listTwo = Arrays.asList("Four", "Five", "Six");
        List<String> listThree = Arrays.asList("Seven", "Eight", "Nine");
        List<String> listFour = Arrays.asList("Ten", "Eleven", "Twelve");

        Box<String> box = new Box<>(listOne, listTwo, listThree, listFour);

        assertEquals(listOne, box.getListOne());
        assertEquals(listTwo, box.getListTwo());
        assertEquals(listThree, box.getListThree());
        assertEquals(listFour, box.getListFour());
    }

    @Test
    void testBoxConstructor_NullList() {
        List<String> listOne = Arrays.asList("One", "Two", "Three");
        List<String> listTwo = Arrays.asList("Four", "Five", "Six");
        List<String> listThree = Arrays.asList("Seven", "Eight", "Nine");
        List<String> listFour = null;

        assertThrows(IllegalArgumentException.class, () -> new Box<>(listOne, listTwo, listThree, listFour));
    }
}

