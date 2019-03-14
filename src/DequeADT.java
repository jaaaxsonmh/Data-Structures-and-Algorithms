package src;

import java.util.Iterator;

public interface DequeADT<E> {

    void enqueueRear(E element);

    E dequeueFront();

    E first();

    void enqueueFront(E element);

    E dequeueRear();

    E last();

    boolean isEmpty();

    Iterator<E> iterator();

    void clear();

    int size();
}
