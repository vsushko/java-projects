package com.vsushko.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vsushko
 */
public class Main {

    public static void main(String[] args) {
        int numberOfThreads = 200; //or any number you'd like

        List<Thread> threads = new ArrayList<>();

        Barrier barrier = new Barrier(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static class Barrier {
        private final int numberOfWorkers;
        private final Semaphore semaphore = new Semaphore(0);
        private int counter = 0;
        private final Lock lock = new ReentrantLock();

        public Barrier(int numberOfWorkers) {
            this.numberOfWorkers = numberOfWorkers;
        }

        public void barrier() {
            lock.lock();
            boolean isLastWorker = false;
            try {
                counter++;

                if (counter == numberOfWorkers) {
                    isLastWorker = true;
                }
            } finally {
                lock.unlock();
            }

            if (isLastWorker) {
                semaphore.release(numberOfWorkers - 1);
            } else {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class CoordinatedWorkRunner implements Runnable {
        private final Barrier barrier;

        public CoordinatedWorkRunner(Barrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                task();
            } catch (InterruptedException e) {
            }
        }

        private void task() throws InterruptedException {
            // Performing Part 1
            System.out.println(Thread.currentThread().getName()
                    + " part 1 of the work is finished");

            barrier.barrier();

            // Performing Part2
            System.out.println(Thread.currentThread().getName()
                    + " part 2 of the work is finished");
        }
    }

    class SomeClass1 {
        boolean isCompleted = false;

        public synchronized void declareSuccess() throws InterruptedException {
            while (!isCompleted) {
                wait();
            }

            System.out.println("Success!!");
        }

        public synchronized void finishWork() {
            isCompleted = true;
            notify();
        }
    }

    class SomeClass2 {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean isCompleted = false;

        public void declareSuccess() throws InterruptedException {
            lock.lock();
            try {
                while (!isCompleted) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }

            System.out.println("Success!!");
        }

        public void finishWork() {
            lock.lock();
            try {
                isCompleted = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
