package Exercises;

import java.util.*;

public class JosephusQueue extends LinkedQueue {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int numPeople;
        int gap;

        LinkedQueue<Integer> queue = new LinkedQueue<>();

        Scanner in = new Scanner(System.in);

        // get the initial number of soldiers
        System.out.print("Enter the number of soldiers: ");
        numPeople = in.nextInt();
        in.nextLine();

        // get the gap between soldiers
        System.out.print("Enter the gap between soldiers: ");
        gap = in.nextInt();

        for (int i = 1; i <= numPeople; i++) {
            queue.enqueue(i);
        }

        StringBuilder sb = new StringBuilder();

        System.out.print("The initial: " + queue.toString());
        System.out.print("\nRemoval order: ");

        while (!queue.isEmpty())
        {
            for (int i = 0; i < gap; ++i)
            {
                if (i < gap - 1) {
                    queue.enqueue(queue.dequeue()); // skip person; re-attach them to the end of queue
                }else {
                    sb.append(queue.dequeue()).append(" "); // an eliminated person
                }
            }
        }
        
        
        System.out.println(sb.toString());
        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);
        System.out.println(duration + "ms");
    }
}
