package com.vsushko.multithreading;

/**
 * @author vsushko
 */
public class Volatile {

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        Thread.sleep(3000);

        worker.setTerminated(true);
    }
}

class Worker implements Runnable {
    // reads from cache without volatile
    // otherwise reads from main memory
    private volatile boolean isTerminated = false;

    public void run() {
        while (!isTerminated) {
            System.out.println("Hello from worker class...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
}