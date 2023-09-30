package com.vsushko.multithreading;

/**
 * @author vsushko
 */
public class ThreadsPriority {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().setPriority(6);

        System.out.println(Thread.currentThread().getPriority());

        Thread t = new Thread(new ThreadPriorityWorker());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
        System.out.println("This is in th main thread");
    }
}

class ThreadPriorityWorker implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}