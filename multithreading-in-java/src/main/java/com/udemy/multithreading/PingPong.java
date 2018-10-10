package com.udemy.multithreading;

/**
 * @author vsushko
 */
public class PingPong {

    public static void main(String[] args) {
        PingPongMonitor monitor = new PingPongMonitor();
        PingThread t1 = new PingThread(monitor);
        PongThread t2 = new PongThread(monitor);
        t1.start();
        t2.start();
    }
}

class PingThread extends Thread {

    private PingPongMonitor monitor;

    public PingThread(PingPongMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            monitor.waitTurn(PingPongMonitor.PING_TURN);
            System.out.println("Ping");
            monitor.toggleTurn();
        }
    }
}

class PongThread extends Thread {

    private PingPongMonitor monitor;

    public PongThread(PingPongMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            monitor.waitTurn(PingPongMonitor.PONG_TURN);
            System.out.println("Pong");
            monitor.toggleTurn();
        }
    }
}

class PingPongMonitor {
    public static boolean PING_TURN = true;
    public static boolean PONG_TURN = false;

    private boolean turn = PING_TURN;

    public synchronized void waitTurn(boolean oldTurn) {
        if (turn != oldTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void toggleTurn() {
        turn ^= true;
        notify();
    }
}