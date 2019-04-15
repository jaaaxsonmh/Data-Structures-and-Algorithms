package src.assignment1;

/*
 * author Jack Hosking
 * Student ID: 16932920
 */
import java.util.Iterator;

public class DequeTest {

    public static void main(String[] args) {
        // just un-code comment depending on which deque you want to test
        //  ArrayDeque<String> deque = new ArrayDeque<>();
        LinkedDeque<String> deque = new LinkedDeque<>();

        //Creating DSA at the front of deque
        deque.enqueueFront("A");
        deque.enqueueFront("S");
        deque.enqueueFront("D");

        //Creating my name at the rear of deque
        deque.enqueueRear("J");
        deque.enqueueRear("A");
        deque.enqueueRear("C");
        deque.enqueueRear("K");
        deque.enqueueRear("H");
        deque.enqueueRear("O");
        deque.enqueueRear("S");
        deque.enqueueRear("K");
        deque.enqueueRear("I");
        deque.enqueueRear("N");
        deque.enqueueRear("G");

        //test the Deque toString method.
        System.out.println(deque.toString());

        // create deque iterator and print out the value of the current position
        Iterator<String> iterator = deque.iterator();

        System.out.println("Iterating the deque:");
        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.println(value);
        }

        //Print out the size of the deque
        System.out.println("\nSize of the deque: " + deque.size());

        //Print out the first element of the DEQUE
        System.out.println("\nThe front of the deque: " + deque.first());
        System.out.println("The rear of the deque: " + deque.last());
        System.out.println("\ndequeuing the front and the rear");
        deque.dequeueFront();
        deque.dequeueRear();
        System.out.println(deque.toString());
        System.out.println("\ndequeuing the front and the rear twice");
        deque.dequeueFront();
        deque.dequeueRear();
        deque.dequeueFront();
        deque.dequeueRear();
        System.out.println(deque.toString());

        System.out.println("\nIs deque empty? " + deque.isEmpty());
        System.out.println("Size of the deque: " + deque.size());
        deque.clear();
        System.out.println("Clearing the deque");
        System.out.println("Is deque empty? " + deque.isEmpty());
        System.out.println("Size of the deque: " + deque.size());

    }
}
