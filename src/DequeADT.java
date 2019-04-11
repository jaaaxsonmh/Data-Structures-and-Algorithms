package src;

/*
 * author Jack Hosking
 * Student ID: 16932920
 */

import java.util.Iterator;

public interface DequeADT<E> {

    void enqueueRear(E element);

    E dequeueFront();

    E first();

    void enqueueFront(E element);

    void dequeueRear();

    E last();

    boolean isEmpty();

    Iterator<E> iterator();

    void clear();

    int size();
}
