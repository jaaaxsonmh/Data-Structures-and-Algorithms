package exercises;

import java.util.ArrayList;

public class BinarySearchListIterator<E extends Comparable> {

    private ArrayList<E> elements;
    private int comp = 0;

    private BinarySearchListIterator(ArrayList<E> elements) {
        this.elements = elements;
    }


    private int search(E target) {
        if (target == null)
            throw new NullPointerException("search target is null");

        int start = 0;
     

        for (E element : elements) {
            int comparison = target.compareTo(element);
            comp++;
            if (comparison == 0) {
                return start;
            }
            start++;
        }
        return start;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int output = 0;

        while (output <= elements.size() - 1) {
            if (elements.get(output) != null) {
                if (output < elements.size() - 1) {
                    result.append(output).append(": ").append(elements.get(output).toString()).append(", ");
                } else {
                    result.append(output).append(": ").append(elements.get(output).toString()).append(" ");
                }
            }
            output++;
        }
        result.append("]");

        return result.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("jack");
        list.add("me");
        list.add("1");
        list.add("hello");
        list.add("cat");
        list.add("dog");
        list.add("catdog");
        list.add("big man");
        list.add("big");
        list.add("space");
        list.add("90");

        BinarySearchListIterator<String> bsi = new BinarySearchListIterator<>(list);

        String target = "1";

        int index = bsi.search(target);

        System.out.println(bsi.toString());

        if (index >= list.size()) {
            System.out.println(target + " is not contained within the list ");
        } else {
            System.out.println(target + " found at index " + index);
        }
        System.out.println(bsi.comp + " comparisons");
    }
}
