package com.vsushko.multithreading;

/**
 * @author vsushko
 */
public class Synchronized1 {

    private static int counter = 0;

    public static void main(String[] args) {
        process();
        System.out.println(counter);
    }

    private static synchronized void increment() {
        ++counter;
    }

    private static void process() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
