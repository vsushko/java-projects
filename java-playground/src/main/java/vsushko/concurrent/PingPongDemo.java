package vsushko.concurrent;

/**
 * @author vsushko
 */
public class PingPongDemo {

    public static void main(String[] args) {
        PingPongPrinter printer = new PingPongPrinter();
        PingPongThread t1 = new PingPongThread(printer, "Ping");
        PingPongThread t2 = new PingPongThread(printer, "Pong");
        t1.start();
        t2.start();
    }
}

class PingPongThread extends Thread {
    private final PingPongPrinter printer;
    private final String s;

    public PingPongThread(PingPongPrinter printer, String s) {
        this.printer = printer;
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            // put a reference to an object that will serve as the lock
            synchronized (printer) {
                printer.printString(s);
                printer.notify();

                try {
                    printer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class PingPongPrinter {
    public void printString(String s) {
        System.out.println(s);
    }
}

