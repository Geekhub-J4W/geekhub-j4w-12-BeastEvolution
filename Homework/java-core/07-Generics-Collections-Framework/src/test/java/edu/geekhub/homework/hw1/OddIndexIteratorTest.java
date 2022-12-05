package edu.geekhub.homework.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class OddIndexIteratorTest {
    OddIndexIterator<Object> objectOddIndexIterator;

    @Tag("normal work")
    @Tag("hasNext()")
    @Test
    void false_when_no_more_elements() {
        objectOddIndexIterator = new OddIndexIterator<>(new ArrayList<>());

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertFalse(isHasNextElement);
    }


    @Tag("normal work")
    @Tag("hasNext()")
    @Test
    void false_when_left_even_element() {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertFalse(isHasNextElement);
    }

    @Tag("normal work")
    @Tag("hasNext()")
    @Test
    void true_when_have_odd_elements() {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.add(new Object());
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        boolean isHasNextElement = objectOddIndexIterator.hasNext();

        assertTrue(isHasNextElement);
    }

    @Tag("normal work")
    @Tag("next()")
    @Test
    void return_next_when_have_odd_elements() {
        List<Object> objects = new ArrayList<>();
        Object oddElement = new Object();
        objects.add(new Object());
        objects.add(oddElement);
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        Object nextOddElement = objectOddIndexIterator.next();

        assertEquals(oddElement, nextOddElement);
    }

    @Tag("normal work")
    @Tag("next()")
    @Test
    void exception_when_left_even_element() {
        List<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objectOddIndexIterator = new OddIndexIterator<>(objects);

        assertThrows(NoSuchElementException.class, () -> objectOddIndexIterator.next());
    }

    @Tag("normal work")
    @Tag("hasNext()")
    @Test
    void exception_when_no_more_elements() {
        objectOddIndexIterator = new OddIndexIterator<>(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> objectOddIndexIterator.next());
    }
}
