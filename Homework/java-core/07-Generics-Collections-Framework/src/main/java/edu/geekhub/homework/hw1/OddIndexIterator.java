package edu.geekhub.homework.hw1;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class OddIndexIterator<E> implements Iterator<E> {
    public static final int SUMMAND_TO_GO_TO_NEXT_ODD_INDEX = 2;
    private List<E> data;
    int position = 1;

    public OddIndexIterator(List<E> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return position < data.size();
    }

    @Override
    public E next() {
        boolean isNotHasNext = !hasNext();
        if (isNotHasNext) {
            throw new NoSuchElementException();
        }
        E nextValue = data.get(position);
        position += SUMMAND_TO_GO_TO_NEXT_ODD_INDEX;
        return nextValue;
    }
}
