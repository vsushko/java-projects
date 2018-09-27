package vsushko.geeksforgeeks.multithreading;

import java.util.Random;

/**
 * Java program to illustrate Runnable for random number generation
 *
 * @author vsushko
 */
public class RunnableRNG {

    public static void main(String[] args) throws InterruptedException {
        RunnableExample[] randomNumberTasks = new RunnableExample[5];
        for (int i = 0; i < 5; i++) {
            randomNumberTasks[i] = new RunnableExample();
            Thread thread = new Thread(randomNumberTasks[i]);
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(randomNumberTasks[i].get());
        }
    }
}

class RunnableExample implements Runnable {

    // shared object to store result
    private Object result = null;

    @Override
    public void run() {
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(5);

        // As run cannot throw any Exception
        try {
            Thread.sleep(randomNumber * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Store the return value in result when done
        result = randomNumber;

        // wake up threads blocked on the get() method
        synchronized (this) {
            notifyAll();
        }
    }

    public synchronized Object get() throws InterruptedException {
        while (result == null) {
            wait();
        }
        return result;
    }
}