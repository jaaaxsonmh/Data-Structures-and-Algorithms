import java.util.Iterator;

public class DequeTest {

    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.enqueueFront("A");
        deque.enqueueFront("S");
        deque.enqueueFront("D");
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


        System.out.println(deque.toString());

        Iterator<String> iterator = deque.iterator();

        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.println(value);
        }

        System.out.println("Size:" + deque.size());

        System.out.println("Front:" + deque.first());
        System.out.println("Rear:" + deque.last());

        deque.dequeueFront();
        deque.dequeueRear();

        System.out.println(deque.toString());
        System.out.println("Empty: " + deque.isEmpty());
        deque.clear();
        System.out.println("Empty: " + deque.isEmpty());

    }
}
