import java.util.Iterator;

public interface DequeADT<E> {

    public void enqueueRear(E element);

    public E dequeueFront();

    public E first();

    public void enqueueFront(E element);

    public E dequeueRear();

    public E last();

    public boolean isEmpty();

    public Iterator<E> iterator();

    public void clear();

    public int size();
}
