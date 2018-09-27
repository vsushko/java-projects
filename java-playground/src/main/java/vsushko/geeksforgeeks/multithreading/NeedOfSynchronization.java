package vsushko.geeksforgeeks.multithreading;


/**
 * Java program to illustrate need of Synchronization
 *
 * @author vsushko
 */
public class NeedOfSynchronization {

    public static void main(String[] args) {
        MultiThreadTwo t = new MultiThreadTwo();
        t.increment();
        System.out.println(t.getValue());
    }
}

class MultiThreadTwo {
    private int i = 0;

    public void increment() {
        i++;
    }

    public int getValue() {
        return i;
    }
}

