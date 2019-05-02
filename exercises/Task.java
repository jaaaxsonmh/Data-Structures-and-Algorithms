package exercises;

import java.util.Arrays;
import java.util.Comparator;


public class Task<E> implements Comparator<Task<E>> {
    private E task;
    private int priority;

    public Task(){

    }

    public Task(E task, int priority) {
        this.task = task;
        this.priority = priority;
    }

    public E getTask() {
        return task;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compare(Task<E> o1, Task<E> o2) {
        if(o1 != null && o2 != null){
            return Integer.compare(o1.priority, o2.priority);
        } else {
            return 0;
        }


    }
}


class PriorityQueue<E extends Task<E>> {

    private Object[] values = new Object[10];

    public void enqueue(E object){
        Object[] old = values;
        values = new Object[old.length + 1];
        for (int k = 0; k < old.length; k++) {
            values[k + 1] = old[k];
        }
        values[0] = object;
        Arrays.sort(values, new Task());
    }

    public E dequeueMin(){
        E value = findMin();
        Object[] old = values;
        values = new Object[old.length - 1];
        for (int k = 0; k < values.length; k++) {
            values[k] = old[k + 1];
        }
        Arrays.sort(values, new Task());
        return value;
    }

    public E findMin(){
        return (E) values[0];
    }

    public static void main(String[] args) {

        Task task1 = new Task("Sweep", 10);
        Task task2 = new Task("Jump", 3);
        Task task3 = new Task("Run", 18);

        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.enqueue(task1);
        priorityQueue.enqueue(task2);
        priorityQueue.enqueue(task3);


        System.out.println();
        System.out.println("Elements: " + priorityQueue.findMin().getTask());
        priorityQueue.dequeueMin();
        System.out.println("Elements: " + priorityQueue.findMin().getTask());
        priorityQueue.dequeueMin();
        System.out.println("Elements: " + priorityQueue.findMin().getTask());




    }
}

