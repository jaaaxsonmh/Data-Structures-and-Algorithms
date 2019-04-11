package exercises;

public class ArrayStackTests {


    public static void main(String[] args) {
        ArrayStack<Integer> infStack = new ArrayStack<>();
        ArrayStackButBetter<Integer> enfStack = new ArrayStackButBetter<>();

        long startTime = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            infStack.push(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Seth's Stack took: " + (startTime - end) * -1 + " ms");

        long startTime2 = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            enfStack.push(i);
        }
        long end2 = System.currentTimeMillis();

        System.out.println("Jack's Stack took: " + (startTime2 - end2) * -1 + " ms");

    }
}
