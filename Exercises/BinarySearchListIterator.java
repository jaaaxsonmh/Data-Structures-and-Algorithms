package Exercises;

import java.util.ArrayList;

public class BinarySearchListIterator<E extends Comparable> {

    private ArrayList<E> elements;

    private BinarySearchListIterator(ArrayList<E> elements) {
        this.elements = elements;
    }

    public int search(E target) {
        if (target == null)
            throw new NullPointerException("search target is null");
        return search(target, 0, elements.size());
    }

    private int search(E target, int start, int end) {

        for (E element : elements) {
            int comparison = target.compareTo(element);
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
        list.add("ant");
        list.add("bat");
        list.add("cat");
        list.add("cow");
        list.add("dog");
        list.add("eel");
        list.add("fly");
        list.add("fox");
        list.add("owl");
        list.add("pig");
        list.add("rat");

        BinarySearchListIterator<String> bin = new BinarySearchListIterator<>(list);

        String target = "fly";

        int index = bin.search(target);

        System.out.println(bin.toString());

        if (index >= list.size()) {
            System.out.println(target + " not at index " + (-index - 1));
        }else {
            System.out.println(target + " found at index " + index);
        }
    }
}
