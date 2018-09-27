package vsushko.geeksforgeeks.multithreading;

/**
 * Java code for thread creation by implementing the Runnable Interface
 */
public class MultithreadingRunnableDemo implements Runnable {

    @Override
    public void run() {
        try {
            // displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            System.out.println("Thread name: " + Thread.currentThread().getName());
        } catch (Exception ex) {
            // throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
