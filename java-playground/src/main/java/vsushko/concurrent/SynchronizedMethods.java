package vsushko.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedMethods {

    private int sum = 0;
    private int syncSum = 0;

    static int staticSum = 0;

    void calculate() {
        setSum(getSum() + 1);
    }

    synchronized void synchronisedCalculate() {
        setSyncSum(getSyncSum() + 1);
    }

    static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    int getSyncSum() {
        return syncSum;
    }

    private void setSyncSum(int syncSum) {
        this.syncSum = syncSum;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service1 = Executors.newFixedThreadPool(3);
        SynchronizedMethods method1 = new SynchronizedMethods();
        IntStream.range(0, 1000)
                .forEach(count -> service1.submit(method1::calculate));
        service1.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(method1.getSum());

        ExecutorService service2 = Executors.newFixedThreadPool(3);
        SynchronizedMethods method2 = new SynchronizedMethods();
        IntStream.range(0, 1000)
                .forEach(count -> service2.submit(method2::synchronisedCalculate));
        service2.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(method2.getSyncSum());

        ExecutorService service3 = Executors.newCachedThreadPool();
        IntStream.range(0, 1000)
                .forEach(count -> service3.submit(SynchronizedMethods::syncStaticCalculate));
        service3.awaitTermination(100, TimeUnit.MILLISECONDS);
        System.out.println(SynchronizedMethods.staticSum);
    }
}
