package vsushko.geeksforgeeks.multithreading;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java code to illustrate Reentrant locks
 *
 * @author vsushko
 */
public class ReentrantLockTest {
    private static final int MAX_T = 2;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        Runnable w1 = new WorkerWithReentrantLock(lock, "Job1");
        Runnable w2 = new WorkerWithReentrantLock(lock, "Job2");
        Runnable w3 = new WorkerWithReentrantLock(lock, "Job3");
        Runnable w4 = new WorkerWithReentrantLock(lock, "Job4");

        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
}

class WorkerWithReentrantLock implements Runnable {

    private String name;
    private ReentrantLock lock;

    public WorkerWithReentrantLock(ReentrantLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        boolean done = false;
        while (!done) {
            // getting outer lock
            boolean ans = lock.tryLock();

            // returns true if lock is free
            if (ans) {
                try {
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("task name - " + name + " outer lock acquired at "
                            + dateFormat.format(date) + " Doing outer work");
                    Thread.sleep(1500);

                    // getting inner lock
                    lock.lock();

                    try {
                        date = new Date();
                        dateFormat = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("task name - " + name + " inner lock acquired at "
                                + dateFormat.format(date) + " Doing inner work");
                        System.out.println("Lock Hold Count - " + lock.getHoldCount());
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //Inner lock release
                        System.out.println("task name - " + name + " releasing inner lock");

                        lock.unlock();
                    }
                    System.out.println("Lock Hold Count - " + lock.getHoldCount());
                    System.out.println("task name - " + name + " work done");

                    done = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // outer lock release
                    System.out.println("task name - " + name + " releasing outer lock");

                    lock.unlock();
                    System.out.println("Lock Hold Count - " + lock.getHoldCount());
                }
            } else {
                System.out.println("task name - " + name +
                        " waiting for lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}