package com.udemy.concurrentcollections;

import java.util.concurrent.Exchanger;

/**
 * @author vsushko
 */
public class Exchangers {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }

    static class FirstThread implements Runnable {
        private int counter;
        private Exchanger<Integer> exchanger;

        public FirstThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter + 1;
                System.out.println("FirstThread increments the counter: " + counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class SecondThread implements Runnable {
        private int counter;
        private Exchanger<Integer> exchanger;

        public SecondThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter = counter - 1;
                System.out.println("SecondThread decrements the counter: " + counter);

                try {
                    counter = exchanger.exchange(counter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
