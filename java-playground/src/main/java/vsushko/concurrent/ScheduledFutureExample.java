package vsushko.concurrent;

import java.util.concurrent.*;

/**
 * @author vsushko
 */
public class ScheduledFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        @SuppressWarnings("unchecked")
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule((Callable<Object>) () -> {
            System.out.println("Executed!");
            return "Called";
        }, 5, TimeUnit.SECONDS);
        System.out.println(scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }
}
