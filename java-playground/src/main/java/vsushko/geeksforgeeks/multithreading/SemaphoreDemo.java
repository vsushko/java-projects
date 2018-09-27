package vsushko.geeksforgeeks.multithreading;

import java.util.concurrent.Semaphore;

/**
 * Java program to demonstrate use of semaphores locks
 * <p>
 * A semaphore controls access to a shared resource
 * through the use of a counter
 *
 * @author vsushko
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        // creating a Semaphore object
        // with number of permits 1
        Semaphore sem = new Semaphore(1);

        // creating two threads with name A and B
        // note that thread A will increment the count
        // and thread B will decrement the count
        MyThread myThread1 = new MyThread(sem, "A");
        MyThread myThread2 = new MyThread(sem, "B");

        // starting threads A and B
        myThread1.start();
        myThread2.start();

        // waiting for threads A and B
        myThread1.join();
        myThread2.join();

        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("count: " + Shared.count);
    }
}

// a shared resource/class
class Shared {
    // count variable
    static int count = 0;
}

class MyThread extends Thread {
    private Semaphore sem;
    private String threadName;

    public MyThread(Semaphore sem, String threadName) {
        super(threadName);

        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        // run by thread A
        if (this.getName().equals("A")) {
            System.out.println("Starting " + threadName);
            try {
                // first, get a permit
                System.out.println(threadName + " is waiting for a permit.");

                // acquiring the lock
                sem.acquire();

                System.out.println(threadName + " gets a permit ");

                // now, accessing the shared resource
                // other waiting threads will wait,
                // until this thread release the lock
                for (int i = 0; i < 5; i++) {
                    Shared.count++;
                    System.out.println(threadName + ": " + Shared.count);

                    // now, allowing a context switch -- if possible
                    // for thread B to execute
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // release the permit
            System.out.println(threadName + " releases the permit.");
            sem.release();
        } else {
            // run by thread B
            System.out.println("Starting " + threadName);

            try {
                // first, get a permit
                System.out.println(threadName + " is waiting for a permit.");

                // acquiring the lock
                sem.acquire();

                System.out.println(threadName + " gets a permit");

                // now, accessing the shared resource
                // other waiting threads will wait,
                // until this thread release lock
                for (int i = 0; i < 5; i++) {
                    Shared.count--;
                    System.out.println(threadName + ": " + Shared.count);

                    // now, allowing a context switch -- if possible
                    // for thread A to execute
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // release the permit
            System.out.println(threadName + " releases the permit.");
            sem.release();
        }
    }
}