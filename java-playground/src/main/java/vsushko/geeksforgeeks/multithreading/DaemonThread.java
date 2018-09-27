package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to demonstrate the usage of setDaemon() and isDaemon() method
 *
 * @author vsushko
 */
public class DaemonThread extends Thread {

    public DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        // checking whether the thread is Daemon or not
        if (Thread.currentThread().isDaemon()) {
            System.out.println(getName() + " is daemon thread");
        } else {
            System.out.println(getName() + " is user thread");
        }
    }

    public static void main(String[] args) {
        DaemonThread t1 = new DaemonThread("t1");
        DaemonThread t2 = new DaemonThread("t2");
        DaemonThread t3 = new DaemonThread("t3");

        // setting user thread t1 to Daemon
        t1.setDaemon(true);

        // starting first two threads
        t1.start();
        t2.start();

        // setting user thread t3 to Daemon
        t3.setDaemon(true);
        t3.start();
    }
}
