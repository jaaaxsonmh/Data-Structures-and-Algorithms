package Exercises;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedSortedSet<E extends Comparable<E>> extends LinkedList<E> {

    private static final long serialVersionUID = 1L;


    public boolean add(E element) {
        ListIterator<E> itr = listIterator();
        while(true) {
            if (itr.hasNext() == false) {
                itr.add(element);
                System.out.println("Add: " + element);
                return(true);
            }

            E elementInList = itr.next();
            if (elementInList.compareTo(element) > 0) {
                itr.previous();
                itr.add(element);
                System.out.println("Add: " + element);
                return(true);
            }
        }
    }

    public static void main(String[] args) {
        LinkedSortedSet<Integer> list = new LinkedSortedSet<>();

        System.out.println("Current: " + list);

        list.add(10);
        System.out.println("Current: " + list);

        list.add(50);
        System.out.println("Current: " + list);

        list.add(30);
        System.out.println("Current: " + list);

        list.add(90);
        System.out.println("Current: " + list);

        list.add(1);
        System.out.println("Current: " + list);

        list.add(60);
        System.out.println("Current: " + list);

        list.add(1000);
        System.out.println("After: " + list);
    }
}
