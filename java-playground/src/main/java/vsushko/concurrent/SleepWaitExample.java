package vsushko.concurrent;

/***
 * Example of wait() and sleep() methods
 */
public class SleepWaitExample {

    private static final Object LOCK = new Object();

    public static void main(String... args) throws InterruptedException {
        sleepWaitInSynchronizedBlocks();
    }

    private static void sleepWaitInSynchronizedBlocks() throws InterruptedException {
        // called on the thread
        Thread.sleep(1000);
        System.out.println("Thread '" + Thread.currentThread().getName() + "' is woken after sleeping for 1 second");

        synchronized (LOCK) {
            // called on the object, synchronization required
            LOCK.wait(1000);
            System.out.println("Object '" + LOCK + "' is woken after waiting for 1 second");
        }
    }
}

/***
 * Example of waking up a waiting thread
 */
class ThreadA {

    private static final ThreadB b = new ThreadB();

    public static void main(String... args) throws InterruptedException {
        b.start();

        synchronized (b) {
            while (b.sum == 0) {
                System.out.println("Waiting for ThreadB to complete...");
                b.wait();
            }
            System.out.println("ThreadB has completed. Sum from that thread is: " + b.sum);
        }
    }
}

/***
 * Example of waking up a waiting thread
 */
class ThreadB extends Thread {
    int sum;

    @Override
    public void run() {
        synchronized (this) {
            int i = 0;
            while (i < 100000) {
                sum += i;
                i++;
            }
            notify();
        }
    }
}
