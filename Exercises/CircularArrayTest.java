package Exercises;

import java.util.Random;

public class CircularArrayTest<E> {

    public static void main(String[] args) {

        CircularArray<Integer> circularQ = new CircularArray<>(20);
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            circularQ.add(random.nextInt(10));
        }
        System.out.println(circularQ);

        circularQ.remove();
        System.out.println("\nREMOVE ELEMENT\n" + circularQ);

        circularQ.trim();
        System.out.println("\nRESIZING THE ARRAY\n" + circularQ);

        circularQ.remove();
        circularQ.remove();
        circularQ.remove();
        System.out.println("\nREMOVE 3 ELEMENTS\n" + circularQ);

        circularQ.add(50);
        System.out.println("\nADDING ELEMENT\n" + circularQ);

        circularQ.trim();
        System.out.println("\nRESIZING THE FINAL ARRAY\n" + circularQ);

    }
}
