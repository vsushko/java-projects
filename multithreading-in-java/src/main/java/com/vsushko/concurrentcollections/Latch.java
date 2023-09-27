package com.vsushko.concurrentcollections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class Latch {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i + 1, latch));
        }

        try {
            // call for sure that every single worker is done
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequisites are done...");
    }

    static class Worker implements Runnable {
        private int id;
        private CountDownLatch countDownLatch;

        public Worker(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            doWork();
            countDownLatch.countDown();
        }

        private void doWork() {
            System.out.println("Thread with id " + this.id + " starts working");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
