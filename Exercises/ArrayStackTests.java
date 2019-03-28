package Exercises;

public class ArrayStackTests {

    private static long total;

    public static void main(String[] args) {

        ArrayStack<Integer> infStack = new ArrayStack<>();
        ArrayStackButBetter<Integer> enfStack = new ArrayStackButBetter<>();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i < 10000000; i++) {
            infStack.push(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Seth's Stack took: " + (startTime - end) * -1 + " ms");

        startTime = System.currentTimeMillis();

        for (int i = 1; i < 10000000; i++) {
            enfStack.push(i);
        }

        end = System.currentTimeMillis();

        System.out.println("Seth's Stack took: " + (startTime - end) * -1 + " ms");

    }
}
