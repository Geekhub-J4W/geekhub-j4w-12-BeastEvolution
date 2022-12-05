package edu.geekhub.homework.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class OddIndexIteratorTest {
    OddIndexIterator<Object> objectOddIndexIterator;

    @Tag("normal work")
    @Test
    void false_when_no_more_elements() {
        objectOddIndexIterator = new OddIndexIterator<>(new ArrayList<>());

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertFalse(isHasNextElement);
    }


    @Tag("normal work")
    @Test
    void false_when_left_even_element() {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertFalse(isHasNextElement);
    }

    @Tag("normal work")
    @Test
    void true_when_have_odd_elements() {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.add(new Object());
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertTrue(isHasNextElement);
    }
}
