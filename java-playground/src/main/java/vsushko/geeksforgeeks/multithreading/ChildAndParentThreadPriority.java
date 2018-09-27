package vsushko.geeksforgeeks.multithreading;

/** Java program to demonstrate that a child thread gets same priority as parent
 *
 * @author vsushko
 */
public class ChildAndParentThreadPriority extends Thread {

    @Override
    public void run() {
        System.out.println("Inside run method");
    }

    public static void main(String[] args) {
        // main thread priority is 6 not
        Thread.currentThread().setPriority(6);

        System.out.println("main thread priority : " +
                Thread.currentThread().getPriority());

        ChildAndParentThreadPriority t1 = new ChildAndParentThreadPriority();

        // t1 thread is a child of main thread so t1 thread will also have priority 6
        System.out.println("t1 thread priority: " + t1.getPriority());
    }
}
