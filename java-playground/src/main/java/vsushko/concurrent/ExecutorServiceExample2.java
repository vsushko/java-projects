package vsushko.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class ExecutorServiceExample2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(() -> new Task() {
            @Override
            public void run() {
                System.out.println("Task2");
            }
        }.run());
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Task1");
    }
}
