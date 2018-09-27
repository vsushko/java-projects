package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to demonstrate the usage of exception in Daemon() thread
 *
 * @author vsushko
 */
public class DaemonThreadWithException extends Thread{

    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Check if its DaemonThread: " + Thread.currentThread().isDaemon());
    }

    public static void main(String[] args) {
        DaemonThreadWithException t1 = new DaemonThreadWithException();
        DaemonThreadWithException t2 = new DaemonThreadWithException();

        t1.start();

        // exception as the thread is already started
        t1.setDaemon(true);

        t2.start();


    }
}
