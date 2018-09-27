package vsushko.geeksforgeeks.multithreading;

/**
 * Java code to see that all threads are pushed on same stack if we use run()
 * instead of start().
 *
 * @author vsushko
 */
public class ThreadWithoutStart extends Thread {

    @Override
    public void run() {
        try {
            // displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            // throwing an exception
            System.out.println("Exception is caught");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            ThreadWithoutStart withoutStart = new ThreadWithoutStart();
            // start() is replaced with run() for seeing the purpose of start
            withoutStart.run();
        }
    }
}

