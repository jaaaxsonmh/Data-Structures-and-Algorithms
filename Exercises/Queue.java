package Exercises;

public interface Queue<E> {

    boolean add(E e);

    E remove();


    E element();


    boolean isFull();


    boolean isEmpty();

    void trim();
}