package vsushko.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedBlocks {

    private int count = 0;
    private static int staticCount = 0;

    void performSynchronisedTask() {
        synchronized (this) {
            setCount(getCount() + 1);
        }
    }

    static void performStaticSyncTask() {
        synchronized (SynchronizedBlocks.class) {
            setStaticCount(getStaticCount() + 1);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    static int getStaticCount() {
        return staticCount;
    }

    private static void setStaticCount(int staticCount) {
        SynchronizedBlocks.staticCount = staticCount;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        SynchronizedBlocks synchronizedBlocks = new SynchronizedBlocks();

        IntStream.range(0, 1000)
                .forEach(count -> fixedPool.submit(synchronizedBlocks::performSynchronisedTask));
        fixedPool.awaitTermination(500, TimeUnit.MILLISECONDS);

        System.out.println(synchronizedBlocks.getCount());

        ExecutorService cachedPool = Executors.newCachedThreadPool();
        IntStream.range(0, 1000)
                .forEach(count -> cachedPool.submit(SynchronizedBlocks::performStaticSyncTask));
        cachedPool.awaitTermination(500, TimeUnit.MILLISECONDS);

        System.out.println(SynchronizedBlocks.getStaticCount());
    }
}
