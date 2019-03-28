package Exercises;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayStackButBetter<E> implements StackADT<E> {

    private final int INITIAL_CAPACITY = 20;
    protected int size;
    protected E[] elements;

    // default constructor that creates a new stack
    // that is initially empty
    public ArrayStackButBetter() {
        size = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]); // unchecked
    }

    // constructor for creating a new stack which
    // initially holds the elements in the collection c
    public ArrayStackButBetter(Collection<? extends E> c) {
        this();
        for (E element : c)
            push(element);
    }

    // Adds one element to the top of this stack
    public void push(E element) {
        if (size >= elements.length)
            expandCapacity();
        elements[size++] = element;
    }

    // removes and returns the top element from this stack
    public E pop() throws NoSuchElementException {
        if (size > 0) {
            E bottom = elements[elements.length - size + 1];
            elements[size - 1] = null;
            size--;
            return bottom;
        } else
            throw new NoSuchElementException();
    }

    // returns without removing the top element of this stack
    public E peek() throws NoSuchElementException {
        if (size > 0)
            return elements[size - 1];
        else
            throw new NoSuchElementException();
    }

    // returns true if this stack contains no elements
    public boolean isEmpty() {
        return (size == 0);
    }

    // returns the number of elements in this stack
    public int size() {
        return size;
    }

    // returns a string representation of the stack from top to bottom
    public String toString() {
        String output = "[";
        for (int i = size - 1; i >= 0; i--) {
            output += elements[i];
            if (i > 0)
                output += ",";
        }
        output += "]";
        return output;
    }

    // helper method which doubles the current size of the array
    private void expandCapacity() {
        E[] largerArray = (E[]) (new Object[elements.length * 2]);//unchecked
        // copy the elements array to the largerArray
        for (int i = 0; i < size; i++)
            largerArray[i] = elements[i];
        elements = largerArray;
    }
}

