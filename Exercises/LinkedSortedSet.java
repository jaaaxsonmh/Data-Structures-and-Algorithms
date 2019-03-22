package Exercises;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedSortedSet<E extends Comparable<E>> extends LinkedList<E> {

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
        LinkedSortedSet<String> list = new LinkedSortedSet<>();


        // For integer
//        System.out.println("Current: " + list);
//
//        list.add(10);
//        System.out.println("Current: " + list);
//
//        list.add(50);
//        System.out.println("Current: " + list);
//
//        list.add(30);
//        System.out.println("Current: " + list);
//
//        list.add(90);
//        System.out.println("Current: " + list);
//
//        list.add(1);
//        System.out.println("Current: " + list);
//
//        list.add(60);
//        System.out.println("Current: " + list);
//
//        list.add(1000);

        System.out.println("Current: " + list);

        list.add("Jack");
        System.out.println("Current: " + list);

        list.add("A");
        System.out.println("Current: " + list);

        list.add("C");
        System.out.println("Current: " + list);

        list.add("Hosking");
        System.out.println("Current: " + list);

        list.add("Me");
        System.out.println("Current: " + list);

        list.add("Z");
        System.out.println("Current: " + list);

        list.add("end");
        System.out.println("After: " + list);

    }
}