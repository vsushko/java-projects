package vsushko.geeksforgeeks.multithreading;

import java.util.Scanner;

/**
 * Inter-thread Communication
 *
 * To avoid polling java uses three methods: wait(), notify() and notifyAll()
 * They must be used within a synchronized block only
 *
 * @author vsushko
 */
public class InterThreadCommunicationExample {

    public static void main(String[] args) throws InterruptedException {
        final ProduceConsumer pc = new ProduceConsumer();

        // create a thread object that calls pc.produce()
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // create another thread object that calls pc.consume()
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

/**
 * ProduceConsumer class with produce() and consume() methods
 */
class ProduceConsumer {

    // prints a string and waits for consume
    public void produce() throws InterruptedException {
        // synchronized block ensures only one thread running at a time
        synchronized (this) {
            System.out.println("producer thread running");

            // releases the lock on shared resource
            wait();

            // and waits till some other method invokes notify()
            System.out.println("Resumed");
        }
    }

    // sleeps for some time and waits for a key press
    // after key is pressed, it notifies produce()
    public void consume() throws InterruptedException {
        // this makes the produce thread to run first
        Thread.sleep(1000);
        Scanner in = new Scanner(System.in);

        // synchronized block ensures only one thread running at a time
        synchronized (this) {
            System.out.println("Waiting for return key");
            in.nextLine();
            System.out.println("Return key pressed");

            // notifies the produce thread that it can wake up
            notify();

            // sleep
            Thread.sleep(2000);
        }
    }
}

