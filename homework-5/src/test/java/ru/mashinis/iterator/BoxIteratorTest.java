package ru.mashinis.iterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BoxIteratorTest {

    @Test
    void testBoxIterator() {
        List<String> listOne = Arrays.asList("One", "Two", "Three");
        List<String> listTwo = Arrays.asList("Four", "Five", "Six");
        List<String> listThree = Arrays.asList("Seven", "Eight", "Nine");
        List<String> listFour = Arrays.asList("Ten", "Eleven", "Twelve");

        Box<String> box = new Box<>(listOne, listTwo, listThree, listFour);

        Iterator<String> iterator = new BoxIterator<>(box);

        assertTrue(iterator.hasNext());
        assertEquals("One", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Two", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Three", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Four", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Five", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Six", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Seven", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Eight", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Nine", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Ten", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Eleven", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Twelve", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}


