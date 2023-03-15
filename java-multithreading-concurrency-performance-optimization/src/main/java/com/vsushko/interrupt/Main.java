package com.vsushko.interrupt;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author vsushko
 */
public class Main {

    public static void main(String[] args) {
        // Thread thread = new Thread(new BlockingTask());
        // thread.start();
        // thread.interrupt();

        // Thread thread = new Thread(new LongComputationTask(new BigInteger("20000000"), new BigInteger("100000")));
        // thread.setDaemon(true);
        // thread.start();
        // thread.interrupt();
        Thread thread = new Thread(new WaitingForUserInput());
        thread.setName("InputWaitingThread");
        thread.setDaemon(true);
        thread.start();
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

    private static class LongComputationTask implements Runnable {
        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + "=" + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                /* if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }*/
                result = result.multiply(base);
            }
            return result;
        }
    }

    private static class WaitingForUserInput implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    char input = (char) System.in.read();
                    if (input == 'q') {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("An exception was caught " + e);
            }
        }
    }
}
