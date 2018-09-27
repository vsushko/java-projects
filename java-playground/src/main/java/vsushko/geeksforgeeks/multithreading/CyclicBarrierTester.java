package vsushko.geeksforgeeks.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Java program to demonstrate execution on Cyclic Barrier
 *
 * @author vsushko
 */
public class CyclicBarrierTester implements Runnable {

    public static CyclicBarrier newBarrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        // parent thread
        CyclicBarrierTester barrier = new CyclicBarrierTester();

        Thread t1 = new Thread(barrier);
        t1.start();
    }

    @Override
    public void run() {
        System.out.println("Number of parties required to trip the barrier = " + newBarrier.getParties());
        System.out.println("Sum of product and sum = " + (Computation1.product + Computation2.sum));

        // objects on which the child thread has to run
        Computation1 comp1 = new Computation1();
        Computation2 comp2 = new Computation2();

        // creation of child thread
        Thread t1 = new Thread(comp1);
        Thread t2 = new Thread(comp2);

        // moving child thread to runnable state
        t1.start();
        t2.start();

        try {
            CyclicBarrierTester.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // barrier breaks as the number of thread waiting
        // for the barrier at this point = 3
        System.out.println("Sum of product and sum = "
                + (Computation1.product + Computation2.sum));

        // resetting the newBarrier
        newBarrier.reset();
        System.out.println("Barrier reset successful");
    }
}

class Computation1 implements Runnable {
    public static int product = 0;

    @Override
    public void run() {
        product = 2 * 3;
        try {
            CyclicBarrierTester.newBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Computation2 implements Runnable {
    public static int sum = 0;

    @Override
    public void run() {
        // check if newBarrier is broken or not
        System.out.println("Is the barrier broken? - " + CyclicBarrierTester.newBarrier.isBroken());
        sum = 10 + 20;
        try {
            CyclicBarrierTester.newBarrier.await(3000, TimeUnit.MILLISECONDS);

            // number of parties waiting at the barrier
            System.out.println("Number of parties waiting at the barrier " +
                    "at this point = " + CyclicBarrierTester.newBarrier.getNumberWaiting());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}