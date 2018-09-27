package vsushko.collections;

import java.util.PriorityQueue;

/**
 * @author vsushko
 */
public class PriorityQueueExample {

    public static void main(String[] args) {
        // Creating empty priority queue
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        // Adding items to the pQueue using add()
        priorityQueue.add("C");
        priorityQueue.add("C++");
        priorityQueue.add("Java");
        priorityQueue.add("Python");

        // Printing the most priority element
        System.out.println("Head value using peek function: " + priorityQueue.peek());

        // Printing all elements
        System.out.println("The queue elements:");
        for (String queueElement : priorityQueue) {
            System.out.println(queueElement);
        }

        // Removing the top priority element (or head) and
        // printing the modified priorityQueue using poll()
        priorityQueue.poll();
        System.out.println("After removing an element with poll function:");
        priorityQueue.forEach(System.out::println);

        // Removing Java using remove()
        priorityQueue.remove("Java");
        System.out.println("after removing Java with remove function:");
        priorityQueue.forEach(System.out::println);

        // Check if an element is present using contains()
        boolean b = priorityQueue.contains("C");
        System.out.println ( "Priority queue contains C or not?: " + b);

        // Getting objects from the queue using toArray()
        Object[] array = priorityQueue.toArray();
        for (Object element : array) {
            System.out.println ( "Value: " + element.toString()) ;
        }
    }
}

