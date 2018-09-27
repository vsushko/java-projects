package vsushko.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author vsushko
 */
public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        AtomicInteger sharedState = new AtomicInteger();

        // for threads coordination
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // The producer will save a random integer to the sharedState variable,
        // and execute the countDown() method on the countDownLatch,
        // signaling to the consumer that it can fetch a value from the sharedState:
        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom
                    .current()
                    .nextInt();
            sharedState.set(producedElement);
            System.out.println("producedElement = " + producedElement);
            countDownLatch.countDown();
        };

        // The consumer will wait on the countDownLatch using the await() method.
        // When the producer signals that the variable was set,
        // the consumer will fetch it from the sharedState
        Runnable consumer = () -> {
            try {
                countDownLatch.await();
                Integer consumedElement = sharedState.get();
                System.out.println("consumedElement = " + consumedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        executor.execute(producer);
        executor.execute(consumer);

        executor.awaitTermination(500, TimeUnit.MILLISECONDS);
        executor.shutdown();
        System.out.println(countDownLatch.getCount());
    }
}
