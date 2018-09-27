package vsushko.geeksforgeeks.multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Java program to illustrate Callable to return a random number
 *
 * @author vsushko
 */
public class CallableFutureRNGTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask is a concrete class that
        // implements both Runnable and Future
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++) {
            Callable callable = new CallableExample();

            // create the future task with callable
            randomNumberTasks[i] = new FutureTask(callable);

            // as it implements Runnable, create Thread with FutureTask
            Thread thread = new Thread(randomNumberTasks[i]);
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            // as it implements Future, we can call get
            System.out.println(randomNumberTasks[i].get());

            // this method blocks till the result is obtained
            // the get method can throw checked exceptions like when it is interrupted
            // this is the reason for adding the throws clause to main
        }
    }
}

class CallableExample implements Callable {
    @Override
    public Object call() throws Exception {
        // create random number generator
        Random generator = new Random();

        Integer randomNumber = generator.nextInt(5);
        // to simulate a heavy computation
        // we delay the thread for some random time
        Thread.sleep(randomNumber * 1000);

        return randomNumber;
    }
}


