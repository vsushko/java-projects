package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to demonstrate getPriority() and setPriority()
 *
 * @author vsushko
 */
public class ThreadWithPriority extends Thread {

    @Override
    public void run() {
        System.out.println("Inside run method");
    }

    public static void main(String[] args) {
        ThreadWithPriority t1 = new ThreadWithPriority();
        ThreadWithPriority t2 = new ThreadWithPriority();
        ThreadWithPriority t3 = new ThreadWithPriority();

        System.out.println("t1 thread priority : " + t1.getPriority()); // Default 5
        System.out.println("t2 thread priority : " + t2.getPriority()); // Default 5
        System.out.println("t3 thread priority : " + t3.getPriority()); // Default 5
        System.out.println();

        t1.setPriority(2);
        t2.setPriority(5);
        t3.setPriority(8);

        System.out.println("t1 thread priority : " + t1.getPriority());
        System.out.println("t2 thread priority : " + t2.getPriority());
        System.out.println("t3 thread priority : " + t3.getPriority());
        System.out.println();

        // main thread
        System.out.println(Thread.currentThread().getName());
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());

        Thread.currentThread().setPriority(10);
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());
    }
}
