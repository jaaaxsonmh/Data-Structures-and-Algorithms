package Exercises;

import java.util.Random;


    public class CircularArrayTest<E> {

        public static void main(String[] args) {

            ArrayQueue<Integer> circularQ =new ArrayQueue<>(20);
            Random random =new Random();

            for(int i=0;i<15;i++){
                circularQ.add(random.nextInt(3));
            }

            System.out.println(circularQ);
            circularQ.remove();
            System.out.println("Before resize: "+circularQ);
            circularQ.trim();
            System.out.println("After resize: "+circularQ);
            circularQ.remove();

            circularQ.add(1);
            circularQ.add(1);
            circularQ.add(1);
            circularQ.add(1);

            System.out.println(circularQ);
            circularQ.add(1);
            System.out.println(circularQ);
            circularQ.add(1);
            circularQ.add(1);
            circularQ.add(1);
            circularQ.add(1);
            circularQ.add(1);
            System.out.println(circularQ.element());
        }
    }

