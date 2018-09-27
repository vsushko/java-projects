package vsushko.concurrent;

import java.util.concurrent.*;

/**
 * @author vsushko
 */
public class SynchronousQueueExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        // The producer will call a put() method that will block until
        // some other thread takes an element from the queue
        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom
                    .current()
                    .nextInt();
            try {
                queue.put(producedElement);
                System.out.println("producedElement = " + producedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };
        // The consumer will simply retrieve that element using the take() method
        Runnable consumer = () -> {
            try {
                Integer consumedElement = queue.take();
                System.out.println("consumedElement = " + consumedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        System.out.println(queue.size());
    }
}
