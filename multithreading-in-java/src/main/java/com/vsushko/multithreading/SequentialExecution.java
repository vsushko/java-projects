package com.vsushko.multithreading;

/**
 * @author vsushko
 */
public class SequentialExecution {

    public static void main(String[] args) throws InterruptedException {
        // Thread t1 = new Thread(new Runner1());
        // Thread t2 = new Thread(new Runner2());
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finished the tasks");
    }
}

class Runner1 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner2 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread1: " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread2: " + i);
        }
    }
}