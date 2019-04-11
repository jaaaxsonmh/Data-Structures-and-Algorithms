package exercises;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {

    private int front = 0;
    private int rear = -1;
    private E[] elements;
    private int size = 0;

    ArrayQueue(int intialCapacity) {
        if (intialCapacity < 0) {
            throw new IllegalArgumentException("Queue Underflow");
        }
        elements = (E[]) new Object[intialCapacity];
    }

    @Override
    public boolean add(E e) {
        if (!isFull()) {
            rear = (rear + 1) % elements.length;
            elements[rear] = e;
            size++;
            return true;
        }
        return false;
    }


    @Override
    public E remove() {
        E element = null;
        if (!isEmpty()) {
            if (front == elements.length - 1) {
                front = (front + 1) % elements.length;
            }
            element = elements[front];
            elements[front] = null;
            front++;
            --size;
        }
        return element;
    }

    @Override
    public E element() {
        E element = null;
        if (!isEmpty()) {
            element = elements[front];
        }
        return element;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void trim() {
        E[] dest = (E[]) new Object[size];
        if (front < rear) {
            System.arraycopy(elements, front, dest, front - 1, rear);
        } else {
            System.arraycopy(elements, front, dest, 0, size - front + 1);
            System.arraycopy(elements, 0, dest, size - front + 1, front - rear);
            front = 0;
            rear = size;
        }
        elements = dest;
    }

    @Override
    public String toString() {
        return "Front: " + front + "\nRear: " + rear
                + "\nElements: " + Arrays.toString(elements)
                + "\nsize=" + size;
    }
}

