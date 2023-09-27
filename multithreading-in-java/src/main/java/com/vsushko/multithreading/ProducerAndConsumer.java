package com.vsushko.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

    static class Processor {
        private final int LIMIT = 5;
        private final int BOTTOM = 0;
        private final Object lock = new Object();
        private List<Integer> list = new ArrayList<Integer>();
        private int value = 0;

        public void produce() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.size() == LIMIT) {
                        System.out.println("Waiting for removing items from the list");
                        lock.wait();
                    } else {
                        System.out.println("Adding: " + value);
                        list.add(value);
                        value++;
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }
        }

        public void consumer() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.size() == BOTTOM) {
                        System.out.println("Waiting for adding items to the list...");
                        lock.wait();
                    } else {
                        System.out.println("Removed " + list.remove(--value));
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }
        }
    }
}

