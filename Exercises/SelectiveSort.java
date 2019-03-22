package Exercises;

import java.util.Random;

public class SelectiveSort {

    // define size of array
    private static final int ARRAY_SIZE = 12;

    public static void main(String args[]) {
        Random rand = new Random();
        SelectiveSort sort = new SelectiveSort();
        int[] arr = new int[ARRAY_SIZE];

        //populate array with 10 random value
        for (int i = 0; i <= ARRAY_SIZE - 1; i++) {
            int random = rand.nextInt((100 - 1) + 1) + 1;
            arr[i] = random;
        }

        // print array unsorted
        System.out.println("Unsorted Array");
        sort.printArray(arr);

        //call the sorting function
        sort.sort(arr);

        // print array sorted
        System.out.println("Sorted Array");
        sort.printArray(arr);
    }

    private void printArray(int[] arr) {
        // loop through array length and print value, follow by space.
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void sort(int[] arr) {
        int n = arr.length;

        // One by one move through the array
        for (int i = 0; i < n - 1; i++) {
            // Find which index is the minimum value
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum index element with the first
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}
