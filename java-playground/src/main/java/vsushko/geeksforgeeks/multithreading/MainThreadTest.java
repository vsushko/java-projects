package vsushko.geeksforgeeks.multithreading;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class MainThreadTest {

    public static void main(String[] args) {

        // getting reference to Main thread
        Thread thread = Thread.currentThread();

        // getting name of Main thread
        System.out.println("Current thread: " + thread.getName());

        // changing the name of Main thread
        thread.setName("Vasya");
        System.out.println("After name change: " + thread.getName());

        // getting priority of Main thread
        System.out.println("Main thread priority: "+ thread.getPriority());

        // setting priority of Main thread to MAX(10)
        thread.setPriority(MAX_PRIORITY);

        System.out.println("Main thread new priority: "+ thread.getPriority());

        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread");
        }

        // Main thread creating a child thread
        ChildThread childThread = new ChildThread();

        // getting priority of child thread
        // which will be inherited from Main thread
        // as it is created by Main thread
        System.out.println("Child thread priority: " + childThread.getPriority());

        // setting priority of Main thread to MIN(1)
        childThread.setPriority(MIN_PRIORITY);

        System.out.println("Child thread new priority: "+ childThread.getPriority());

        // starting child thread
        childThread.start();
    }
}

/**
 * Child Thread class
 */
class ChildThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Child thread");
        }
    }
}
