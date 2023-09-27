package com.vsushko.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author vsushko
 */
public class BlockingQueues {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }

    static class FirstWorker implements Runnable {
        private BlockingQueue<Integer> blockingQueue;

        public FirstWorker(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            int counter = 0;

            while (true) {
                try {
                    blockingQueue.put(counter);
                    System.out.println("Putting items to the queue... " + counter);
                    counter++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class SecondWorker implements Runnable {
        private BlockingQueue<Integer> blockingQueue;

        public SecondWorker(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {

            while (true) {
                try {
                    int number = blockingQueue.take();
                    System.out.println("Taking item from the queue... " + number);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
