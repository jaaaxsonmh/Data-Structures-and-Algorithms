package src.assignment2;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @author Jack Hosking Student ID: 16932920
 */
public class ArraySorter<E extends Comparable> {

    public int swaps;
    public int comparisons;

    public void selectionSort(E[] list) {
        int indexMin; // index of least element
        E temp; // temporary reference to an element for swapping
        for (int i = 0; i < list.length - 1; i++) {  // find the least element that has index>=i
            indexMin = i;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j].compareTo(list[indexMin]) < 0) {
                    indexMin = j;
                }
            }
            // swap the element at indexMin with the element at i
            temp = list[indexMin];
            list[indexMin] = list[i];
            list[i] = temp;
        }
    }

    public void insertionSort(E[] list) {
        E elementInsert;
        for (int i = 1; i < list.length; i++) {  // get the element at index i to insert at some index<=i
            elementInsert = list[i];
            // find index where to insert element to maintain 0..i sorted
            int indexInsert = i;
            while (indexInsert > 0
                    && list[indexInsert - 1].compareTo(elementInsert) > 0) {  // shift element at insertIndex-1 along one to make space
                list[indexInsert] = list[indexInsert - 1];
                indexInsert--;
            }
            // insert the element
            list[indexInsert] = elementInsert;
        }
    }

    public void bubbleSort(E[] list) {
        E temp; // temporary reference to an element for swapping
        for (int i = list.length - 1; i >= 0; i--) {  // pass through indices 0..i and bubble (swap) adjacent
            // elements if out of order
            for (int j = 0; j < i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {  // swap the elements at indices j and j+1
                    temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    /*
    * The cocktail sort can be optimized by adding two variables that dynamically track the end, and start
    * At the first iteration these are initilized to the end position, and the start position. 
    * We then take these starts and ends and add them into where the index is for the top-> bottom loop
    * and the index is for the bottom -> top loop
    * where the bottom starts at the start (0) and the top starts at the end (list.length) 
    * once we sort an element we can decrease the end or start. If the element is pushed from bottom to top,
    * then the last index is now sorted so the end can be decreased by 1. and if we push from top to bottom then
    * the first index is now sorted so we can increase the start by 1. (rinse and repeat for every operation)
    *
    * This decreases the part of the list that is sorted every time and will essentially half the amount of operations. 
    * Without this operation being optimized, it would go from the start (0) to the end (list.length) every time.
    * With this operation being optimized, it would go from the start (0) + n to the end (list.length) - n every time.
    * and each operation reduces the amount of checked elements in the list, because they are now sorted and dont need to be touched.
    * in turn we will have less comparisons than the unefficient cocktail sorter, but swaps will be the same (for the same lengths of lists
    *
    * Asymptotic Complexity: O(n^2)
     */
    public void cocktailSort(E[] list) {
        int end = list.length;
        int start = 0;
        comparisons = 0;
        swaps = 0;
        boolean swapped = true;
        E temp;

        while (swapped) {
            swapped = false;
            //bottom to top comparison.
            for (int i = start; i < end - 1; i++) {
                comparisons++;
                if (list[i].compareTo(list[i + 1]) > 0) {  // swap the elements at indices i and i+1
                    temp = list[i + 1];
                    list[i + 1] = list[i];
                    list[i] = temp;
                    swapped = true;
                    System.out.println(Arrays.toString(list));
                    swaps++;
                }
            }
            // if swapped is false, then nothing has been moved and we can exit as list will be sorted.
            if (!swapped) {
                break;
            }

            //reset flag
            swapped = false;

            // decrease end point
            //element at end is now sorted
            end--;

            //top to bottom comparison.
            for (int i = end - 1; i >= start; i--) {
                comparisons++;
                if (list[i].compareTo(list[i + 1]) > 0) {  // swap the elements at indices i and i+1
                    temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                    System.out.println(Arrays.toString(list));
                    swaps++;
                }
            }

            //increase starting point
            //element at start is now sorted
            start++;
        }

    }

    public void cocktailSortSlow(E[] list) {
        boolean swapped = true;
        E temp;
        comparisons = 0;
        swaps = 0;
        while (swapped) {
            swapped = false;

            //bottom to top comparison.
            for (int i = 0; i < list.length - 1; i++) {
                comparisons++;
                if (list[i].compareTo(list[i + 1]) > 0) {  // swap the elements at indices i and i+1
                    temp = list[i + 1];
                    list[i + 1] = list[i];
                    list[i] = temp;
                    swapped = true;
                    System.out.println(Arrays.toString(list));
                    swaps++;
                }
            }

            // if swapped is false, then nothing has been moved and we can exit as list will be sorted.
            if (!swapped) {
                break;
            }

            //reset flag
            swapped = false;

            //top to bottom comparison.
            for (int i = list.length - 2; i >= 0; i--) {
                comparisons++;
                if (list[i].compareTo(list[i + 1]) > 0) {  // swap the elements at indices i and i+1
                    temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                    System.out.println(Arrays.toString(list));
                    swaps++;
                }
            }
        }
    }

    public void quickSort(E[] list) {
        quickSortSegment(list, 0, list.length);
    }

    // recursive method which applies quick sort to the portion
    // of the array between start (inclusive) and end (exclusive)
    private void quickSortSegment(E[] list, int start, int end) {
        if (end - start > 1) // then more than one element to sort
        {  // partition the segment into two segments
            int indexPartition = partition(list, start, end);
            // sort the segment to the left of the partition element
            quickSortSegment(list, start, indexPartition);
            // sort the segment to the right of the partition element
            quickSortSegment(list, indexPartition + 1, end);
        }
    }

    // use the index start to partition the segment of the list
    // with the element at start as the partition element
    // separating the list segment into two parts, one less than
    // the partition, the other greater than the partition
    // returns the index where the partition element ends up
    private int partition(E[] list, int start, int end) {
        E temp; // temporary reference to an element for swapping
        E partitionElement = list[start];
        int leftIndex = start; // start at the left end
        int rightIndex = end - 1; // start at the right end
        // swap elements so elements at left part are less than
        // partition element and at right part are greater
        while (leftIndex < rightIndex) {  // find element starting from left greater than partition
            while (list[leftIndex].compareTo(partitionElement) <= 0
                    && leftIndex < rightIndex) {
                leftIndex++; // this index is on correct side of partition
            }            // find element starting from right less than partition
            while (list[rightIndex].compareTo(partitionElement) > 0) {
                rightIndex--; // this index is on correct side of partition
            }
            if (leftIndex < rightIndex) {  // swap these two elements
                temp = list[leftIndex];
                list[leftIndex] = list[rightIndex];
                list[rightIndex] = temp;
            }
        }
        // put the partition element between the two parts at rightIndex
        list[start] = list[rightIndex];
        list[rightIndex] = partitionElement;
        return rightIndex;
    }

    public void mergeSort(E[] list) {
        mergeSortSegment(list, 0, list.length);
    }

    // recursive method which applies merge sort to the portion
    // of the array between start (inclusive) and end (exclusive)
    private void mergeSortSegment(E[] list, int start, int end) {
        int numElements = end - start;
        if (numElements > 1) {
            int middle = (start + end) / 2;
            // sort the part to the left of middle
            mergeSortSegment(list, start, middle);
            // sort the part to the right of middle
            mergeSortSegment(list, middle, end);
            // copy the two parts elements into a temporary array
            E[] tempList = (E[]) (new Comparable[numElements]); //unchecked
            for (int i = 0; i < numElements; i++) {
                tempList[i] = list[start + i];
            }
            // merge the two sorted parts from tempList back into list
            int indexLeft = 0; // current index of left part
            int indexRight = middle - start; // current index of right part
            for (int i = 0; i < numElements; i++) {  // determine which element to next put in list
                if (indexLeft < (middle - start))//left part still has elements
                {
                    if (indexRight < (end - start))// right part also has elem
                    {
                        if (tempList[indexLeft].compareTo(tempList[indexRight]) < 0) // left element smaller
                        {
                            list[start + i] = tempList[indexLeft++];
                        } else // right element smaller
                        {
                            list[start + i] = tempList[indexRight++];
                        }
                    } else // take element from left part
                    {
                        list[start + i] = tempList[indexLeft++];
                    }
                } else // take element from right part
                {
                    list[start + i] = tempList[indexRight++];
                }
            }
        }
    }

    // driver main method to test one of the algorithms
    public static void main(String[] args) {
        ArraySorter<Integer> sorter = new ArraySorter<>();
        String[] listString = {"cow", "fly", "dog", "bat", "fox", "cat", "eel",
            "ant"};

        //Easier to visualize the 'cocktail shaking' shape
        Integer[] listInt = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] listInt1 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        System.out.println("Sorting the list: " + Arrays.toString(listInt));

        Instant start = Instant.now();
        sorter.cocktailSort(listInt);
        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();

        // output the results
        System.out.println();
        for (int i = 0; i < listInt.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + listInt[i]);
        }
        System.out.printf("\nExecution time of efficient Cocktail: %d ms. Comparisons: %d, Swaps: %d\n\n", timeElapsed, sorter.comparisons, sorter.swaps);
        System.out.println("Sorting the list: " + Arrays.toString(listInt1));

        Instant start1 = Instant.now();
        sorter.cocktailSortSlow(listInt1);
        Instant finish1 = Instant.now();

        long timeElapsed1 = Duration.between(start1, finish1).toMillis();

        System.out.println();
        for (int i = 0; i < listInt1.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + listInt1[i]);
        }
        System.out.printf("\nExecution time of slow Cocktail: %d ms. Comparisons: %d, Swaps: %d\n", timeElapsed1, sorter.comparisons, sorter.swaps);
    }
}
