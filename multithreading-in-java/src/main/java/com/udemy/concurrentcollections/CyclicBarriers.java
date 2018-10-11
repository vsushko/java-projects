package com.udemy.concurrentcollections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class CyclicBarriers {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("All the tasks are finished");
            }
        });

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i + 1, cyclicBarrier));
        }

        executorService.shutdown();
    }

    static class Worker implements Runnable {
        private int id;
        private Random random;
        private CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.random = new Random();
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            doWork();
        }

        public void doWork() {
            System.out.println("Thread with id " + id + " starts the task...");
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread with id " + id + " finished");

            try {
                cyclicBarrier.await();
                System.out.println("After await...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return "" + this.id;
        }
    }
}
