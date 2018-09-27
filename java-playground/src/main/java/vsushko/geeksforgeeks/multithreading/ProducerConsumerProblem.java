package vsushko.geeksforgeeks.multithreading;

import java.util.LinkedList;

/**
 * Java program to implement solution of producer consumer problem
 *
 * @author vsushko
 */
public class ProducerConsumerProblem {

    public static void main(String[] args) throws InterruptedException {
        // object of a class that has both produce() and consume() methods
        final PC pc = new PC();

        // create producer thread
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // create consumer thread
        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }
}

// this class has a list, producer (adds items to list
// and consumer (removes items)
class PC {
    // create a list shared by producer and consumer
    // size of list is 2
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    // function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // producer thread waits while list is full
                while (list.size() == capacity) {
                    wait();
                }
                System.out.println("Producer produced-" + value);

                // to insert the jobs in the list
                list.add(value++);

                // notifies the consumer thread that now it can start consuming
                notify();

                // makes the working of program easier to understand
                Thread.sleep(1000);
            }
        }
    }

    // function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list is empty
                while (list.size() == 0) {
                    wait();
                }
                // retrieve the first element from list
                int val = list.removeFirst();

                System.out.println("Consumer consumed-" + val);

                // wake up producer thread notify()
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }
}