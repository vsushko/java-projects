package vsushko.geeksforgeeks.multithreading;

/**
 * @author vsushko
 */
public class MyThreadWithYield extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}

class YieldDemo {
    public static void main(String[] args) {
        MyThreadWithYield t = new MyThreadWithYield();
        t.start();

        for (int i = 0; i < 5; i++) {
            // Control passes to child thread
            Thread.yield();
            // After execution of child Thread main thread takes over
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}
