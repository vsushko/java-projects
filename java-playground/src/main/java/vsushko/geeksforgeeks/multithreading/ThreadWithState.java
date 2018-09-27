package vsushko.geeksforgeeks.multithreading;

/**
 * Java program to demonstrate thread states
 */
public class ThreadWithState implements Runnable {

    @Override
    public void run() {
        // moving thread2 to timed waiting state
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("State of thread1 while it called join() method on thread2 - "+
                ThreadWithStateTest.thread1.getState());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
