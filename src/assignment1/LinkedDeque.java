package src.assignment1;

/*
 * author Jack Hosking
 * Student ID: 16932920
 */

import java.util.Iterator;

public class LinkedDeque<E> implements DequeADT<E> {

    private LinkedNode front, rear;
    private int size = 0;

    @Override
    public void enqueueRear(E element) {
        LinkedNode node = new LinkedNode(element);
        if (isEmpty()) {
            this.front = node;
            this.rear = node;
        } else {
            node.setPrevious(rear);
            this.rear.setNext(node);
            this.rear = node;
        }
        this.size++;
    }

    @Override
    public E dequeueFront() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }

        E value = front.getValue();
        this.front = this.front.getNext();
        this.size--;
        if (size == 0) {
            this.rear = null;
        } else {
            this.front.setPrevious(null);
        }
        return value;
    }

    @Override
    public E first() {
        return front.getValue();
    }

    @Override
    public void enqueueFront(E element) {
        LinkedNode node = new LinkedNode(element);
        if (isEmpty()) {
            this.front = node;
            this.rear = node;
        } else {
            node.setNext(front);
            this.front.setPrevious(node);
            this.front = node;
        }
        this.size++;
    }

    @Override
    public void dequeueRear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow.");
        }

        E value = rear.getValue();
        this.rear = this.rear.getPrevious();
        this.size--;
        if (size == 0) {
            this.front = null;
        } else {
            this.rear.setNext(null);
        }
    }

    @Override
    public E last() {
        return rear.getValue();
    }

    @Override
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedDequeIterator();
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeueFront();
        }
    }

    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedDeque = ");
        LinkedNode node = this.front;
        while (node != null) {
            sb.append(node.getValue() + " ");
            node = node.getNext();
        }
        return sb.toString();
    }

    private class LinkedNode {

        private E value;
        private LinkedNode next;
        private LinkedNode previous;

        public LinkedNode(E info) {
            this.value = info;
        }

        private LinkedNode getNext() {
            return next;
        }

        private LinkedNode getPrevious() {
            return previous;
        }

        private E getValue() {
            return value;
        }

        private void setNext(LinkedNode next) {
            this.next = next;
        }

        private void setPrevious(LinkedNode previous) {
            this.previous = previous;
        }

    }

    class LinkedDequeIterator implements Iterator<E> {

        LinkedNode current;

        @Override
        public boolean hasNext() {
            if (current == null && front != null) {
                return true;
            }
            return current != null && current.getNext() != null;
        }

        @Override
        public E next() {
            if (current == null) {
                current = front;
            } else {
                current = current.getNext();
            }
            return current.getValue();
        }
    }
}
