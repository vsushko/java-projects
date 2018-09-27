package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to illustrate Deadlock in multithreading
 *
 * @author vsushko
 */
public class DeadlockDemo {

    public static void main(String[] args) {
        SharedWithSyncMethods s1 = new SharedWithSyncMethods();
        SharedWithSyncMethods s2 = new SharedWithSyncMethods();

        // creating first thread and starting it
        Thread1 t1 = new Thread1(s1, s2);
        t1.start();

        // creating second thread and starting it
        Thread2 t2 = new Thread2(s1, s2);
        t2.start();

        // sleeping main thread
        Util.sleep(2000);
    }
}

class Util {
    // util class to sleep a thread
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// this class is shared by both threads
class SharedWithSyncMethods {
    // first synchronized method
    synchronized void test1(SharedWithSyncMethods s2) {
        System.out.println("test1-begin");
        Util.sleep(1000);
        // taking object lock of s2 enters into test2 method
        s2.test2(this);
        System.out.println("test1-end");
    }

    // second synchronized method
    synchronized void test2(SharedWithSyncMethods s1) {
        System.out.println("test2-begin");
        Util.sleep(1000);
        // taking object lock of s1 enters into test method
        s1.test1(this);
        System.out.println("test2-end");
    }
}

class Thread1 extends Thread {
    private SharedWithSyncMethods s1;
    private SharedWithSyncMethods s2;

    // constructor to initialize fields
    public Thread1(SharedWithSyncMethods s1, SharedWithSyncMethods s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // run method to start a thread
    @Override
    public void run() {
        // taking object lock of s1 enters into test1 method
        s1.test1(s2);
    }
}

class Thread2 extends Thread {
    private SharedWithSyncMethods s1;
    private SharedWithSyncMethods s2;

    // constructor to initialize fields
    public Thread2(SharedWithSyncMethods s1, SharedWithSyncMethods s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // run method to start a thread
    @Override
    public void run() {
        // taking object lock of s1 enters into test1 method
        s2.test2(s1);
    }
}