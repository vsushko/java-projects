package com.udemy.multithreading;

/**
 * @author vsushko
 */
public class LongIncrementer {
    private static volatile long value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    value++;
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    value++;
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(value);
    }
}

