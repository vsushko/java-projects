package vsushko.geeksforgeeks.multithreading;

/**
 * Example illustrates multiple threads are executing
 * on the same Object at same time without synchronization
 *
 * @author vsushko
 */
public class MethodSynchronization {

    public static void main(String[] args) {
        // Object of Line class that is shared among the threads
        Line obj = new Line();

        // creating the threads that are sharing the same Object
        Train train1 = new Train(obj);
        Train train2 = new Train(obj);

        // threads start their execution
        train1.start();
        train2.start();
    }
}

class Line {
    // if multiple threads(trains) will try to access this unsynchronized method,
    // they all will get it. So there is changes that Objects's state will be corrupted
    // if multiple threads(trains) trying to access this synchronized method
    // on the same Object but only one thread will be able to execute it at a time
    synchronized public void getLine() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class Train extends Thread {
    // reference to Line's Object
    private Line line;

    Train(Line line) {
        this.line = line;
    }

    @Override
    public void run() {
        line.getLine();
    }
}

