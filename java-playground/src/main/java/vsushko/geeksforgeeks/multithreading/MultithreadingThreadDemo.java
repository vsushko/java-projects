package vsushko.geeksforgeeks.multithreading;

/**
 * Java code for thread creation by extending the Thread class
 */
public class MultithreadingThreadDemo extends Thread {

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

