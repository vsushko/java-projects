package vsushko.geeksforgeeks.multithreading;

public class MultiThread {

    public static void main(String[] args) {
        // number of threads
        final int THREADS_COUNT = 8;

        for (int i = 0; i < THREADS_COUNT; i++) {
            MultithreadingThreadDemo demo = new MultithreadingThreadDemo();
            demo.start();
        }

        for (int i = 0; i < THREADS_COUNT; i++) {
            Thread thread = new Thread(new MultithreadingRunnableDemo());
            thread.start();
        }
    }
}
