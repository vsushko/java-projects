package vsushko.geeksforgeeks.multithreading;

/**
 * A Java program to demonstrate working of synchronized
 *
 * @author vsushko
 */
public class SyncDemo {

    public static void main(String[] args) {
        Sender sender = new Sender();
        ThreadedSend s1 = new ThreadedSend("Hi! ", sender);
        ThreadedSend s2 = new ThreadedSend("Bye! ", sender);

        // start two threads of ThreadedSend type
        s1.start();
        s2.start();

        // wait for threads to end
        try {
            s1.join();
            s2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

/**
 * A class used to send a message
 */
class Sender {

    // define the whole send() block as synchronized
    public void send(String msg) {
        System.out.println("Sending\t" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("\n" + msg + "Sent");
    }
}

/**
 * A class used to send a message with synchronized in method declaration
 */
class Sender2 {

    // define the whole send() block as synchronized
    public synchronized void send(String msg) {
        System.out.println("Sending\t" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        System.out.println("\n" + msg + "Sent");
    }
}


/**
 * A class used to send a message with synchronized in method
 */
class Sender3 {

    // define the whole send() block as synchronized
    public void send(String msg) {
        synchronized (this) {
            System.out.println("Sending\t" + msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
            System.out.println("\n" + msg + "Sent");
        }
    }
}

/**
 * Class for send a message using Threads
 */
class ThreadedSend extends Thread {
    private String msg;
    private Thread t;
    private Sender sender;

    /**
     * Constructor with parameters
     *
     * @param msg    message object
     * @param sender string message to be sent
     */
    public ThreadedSend(String msg, Sender sender) {
        this.msg = msg;
        this.sender = sender;
    }

    @Override
    public void run() {
        // only one thread can send a message at a time
        synchronized (sender) {
            // synchronizing the send object
            sender.send(msg);
        }
    }
}
