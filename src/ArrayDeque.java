package src;

/*
 * author Jack Hosking
 * Student ID: 16932920
 */

import java.util.Iterator;

public class ArrayDeque<E> implements DequeADT<E> {

    private E[] values = (E[]) new Object[0];

    @Override
    public void enqueueRear(E element) {
        E[] old = values;
        values = (E[]) new Object[old.length + 1];
        for (int k = 0; k < old.length; k++) {
            values[k] = old[k];
        }
        values[values.length - 1] = element;
    }

    @Override
    public E dequeueFront() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }
        E value = first();
        E[] old = values;
        values = (E[]) new Object[old.length - 1];
        for (int k = 0; k < values.length; k++) {
            values[k] = old[k + 1];
        }
        return value;
    }

    @Override
    public void enqueueFront(E element) {
        E[] old = values;
        values = (E[]) new Object[old.length + 1];
        for (int k = 0; k < old.length; k++) {
            values[k + 1] = old[k];
        }
        values[0] = element;
    }

    @Override
    public void dequeueRear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }
        E value = last();
        E[] old = values;
        values = (E[]) new Object[old.length - 1];
        for (int k = 0; k < values.length; k++) {
            values[k] = old[k];
        }
    }

    @Override
    public E first() {

        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }
        return values[0];
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }
        return values[values.length - 1];
    }

    @Override
    public boolean isEmpty() {
        return this.values.length == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator<>();
    }

    @Override
    public void clear() {
        values = (E[]) new Object[0];
    }

    @Override
    public int size() {
        return values.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ArrayDeque = ");
        for (E value : values) {
            sb.append(value + " ");
        }
        return sb.toString();
    }

    class ArrayDequeIterator<E> implements Iterator<E> {

        int index = -1;

        @Override
        public boolean hasNext() {
            return values.length - 1 > index;
        }

        @Override
        public E next() {
            return (E) values[++index];
        }
    }
}
