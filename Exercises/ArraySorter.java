package Exercises;

/**
 * A class which includes common algorithms for sorting arrays of Comparable
 * objects in place. Note the elements of the array are presumed to be non-null
 */
public class ArraySorter<E extends Comparable> {

	public static int comparison;
	public static int swaps;

	public void selectionSort(E[] list) {
		int indexMin; // index of least element
		E temp; // temporary reference to an element for swapping
		for (int i = 0; i < list.length - 1; i++) { // find the least element that has index>=i
			indexMin = i;
			for (int j = i + 1; j < list.length; j++) {
				comparison++;
				if (list[j].compareTo(list[indexMin]) < 0) {
					indexMin = j;
					swaps++;
				}
			}
			// swap the element at indexMin with the element at i
			swaps++;
			temp = list[indexMin];
			list[indexMin] = list[i];
			list[i] = temp;
		}
	}

	public void insertionSort(E[] list) {
		E elementInsert;
		for (int i = 1; i < list.length; i++) { // get the element at index i to insert at some index<=i
			elementInsert = list[i];
			// find index where to insert element to maintain 0..i sorted
			int indexInsert = i;
			while (indexInsert > 0 && list[indexInsert - 1].compareTo(elementInsert) > 0) {
				comparison++;// shift element at insertIndex-1 along one to make space
				list[indexInsert] = list[indexInsert - 1];
				indexInsert--;
			}
			// insert the element
			swaps++;
			list[indexInsert] = elementInsert;
		}
	}

	public void bubbleSort(E[] list) {
		E temp; // temporary reference to an element for swapping
		for (int i = list.length - 1; i >= 0; i--) {// pass through indices 0..i and bubble (swap) adjacent
			// elements if out of order
			for (int j = 0; j < i; j++) {
				comparison++;
				if (list[j].compareTo(list[j + 1]) > 0) { // swap the elements at indices j and j+1
					swaps++;
					temp = list[j + 1];
					list[j + 1] = list[j];
					list[j] = temp;
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
		{ // partition the segment into two segments
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
		while (leftIndex < rightIndex) { // find element starting from left greater than partition
			swaps++;
			while (list[leftIndex].compareTo(partitionElement) <= 0 && leftIndex < rightIndex) {
				comparison++;
				leftIndex++; // this index is on correct side of partition
			} // find element starting from right less than partition
			while (list[rightIndex].compareTo(partitionElement) > 0) {
				comparison++;
				rightIndex--; // this index is on correct side of partition
			}
			comparison++;
			if (leftIndex < rightIndex) { // swap these two elements
				swaps++;
				temp = list[leftIndex];
				list[leftIndex] = list[rightIndex];
				list[rightIndex] = temp;
			}
		}
		// put the partition element between the two parts at rightIndex
		list[start] = list[rightIndex];
		list[rightIndex] = partitionElement;
		swaps++;
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
			comparison++;
			mergeSortSegment(list, start, middle);
			// sort the part to the right of middle
			comparison++;
			mergeSortSegment(list, middle, end);
			// copy the two parts elements into a temporary array
			E[] tempList = (E[]) (new Comparable[numElements]); // unchecked
			for (int i = 0; i < numElements; i++) {
				tempList[i] = list[start + i];
			}
			// merge the two sorted parts from tempList back into list
			int indexLeft = 0; // current index of left part
			int indexRight = middle - start; // current index of right part
			for (int i = 0; i < numElements; i++) {
				comparison++;// determine which element to next put in list
				if (indexLeft < (middle - start))// left part still has elements
				{
					comparison++;
					if (indexRight < (end - start))// right part also has elem
					{
						comparison++;
						if (tempList[indexLeft].compareTo(tempList[indexRight]) < 0) // left element smaller
						{
							swaps++;
							list[start + i] = tempList[indexLeft++];
						} else // right element smaller
						{
							swaps++;
							list[start + i] = tempList[indexRight++];
						}
					} else // take element from left part
					{
						swaps++;
						list[start + i] = tempList[indexLeft++];
					}
				} else // take element from right part
				{
					swaps++;
					list[start + i] = tempList[indexRight++];
				}
			}
		}
	}

	// driver main method to test one of the algorithms
	public static void main(String[] args) {
		ArraySorter<String> sorter = new ArraySorter<String>();
		
		
		
		
		String[] list = { "cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant" };
		System.out.println("Type: Quick Sort");
		
		long startTime =  System.nanoTime();
		sorter.quickSort(list);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000;
		System.out.println(duration + " ms");
		
		System.out.println("Comparison: " + comparison);
		System.out.println("Swaps: " + swaps);
		for (int i = 0; i < list.length; i++) {
			System.out.print(((i > 0) ? ", " : "") + list[i]);
		}
		System.out.println("\n");
		comparison = 0;
		swaps = 0;
		// bubbleSort insertionSort selectionSort mergesort

		String[] list2 = { "cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant" };
		System.out.println("Type: bubbleSort");
		
		startTime =  System.nanoTime();
		sorter.bubbleSort(list2);
		 endTime = System.nanoTime();
		 duration = (endTime - startTime) / 1000;
		 System.out.println(duration + " ms");
		System.out.println("Comparison: " + comparison);
		System.out.println("Swaps: " + swaps);
		for (int i = 0; i < list2.length; i++) {
			System.out.print(((i > 0) ? ", " : "") + list2[i]);
		}
		System.out.println("\n");
		comparison = 0;
		swaps = 0;

		String[] list1 = { "cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant" };
		System.out.println("Type: InsertionSort");
		
		startTime =  System.nanoTime();
		sorter.insertionSort(list1);
		 endTime = System.nanoTime();
		 duration = (endTime - startTime) / 1000;
		 System.out.println(duration + " ms");
		System.out.println("Comparison: " + comparison);
		System.out.println("Swaps: " + swaps);
		for (int i = 0; i < list1.length; i++) {
			System.out.print(((i > 0) ? ", " : "") + list1[i]);
		}
		System.out.println("\n");
		comparison = 0;
		swaps = 0;

		String[] list3 = { "cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant" };
		System.out.println("Type: Selection Sort");
		startTime =  System.nanoTime();
		sorter.selectionSort(list3);
		 endTime = System.nanoTime();
		 duration = (endTime - startTime) / 1000;
		 System.out.println(duration + " ms");
		System.out.println("Comparison: " + comparison);
		System.out.println("Swaps: " + swaps);
		for (int i = 0; i < list3.length; i++) {
			System.out.print(((i > 0) ? ", " : "") + list3[i]);
		}
		System.out.println("\n");
		comparison = 0;
		swaps = 0;

		String[] list4 = { "cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant" };
		System.out.println("Type: Merge Sort");
		
		startTime =  System.nanoTime();
		sorter.mergeSort(list3);
		 endTime = System.nanoTime();
		 duration = (endTime - startTime) / 1000;
		 System.out.println(duration + " ms");
		System.out.println("Comparison: " + comparison);
		System.out.println("Swaps: " + swaps);
		for (int i = 0; i < list4.length; i++) {
			System.out.print(((i > 0) ? ", " : "") + list4[i]);
		}
		System.out.println("\n");
		comparison = 0;
		swaps = 0;
	}
}
