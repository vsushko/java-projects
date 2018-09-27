package vsushko.concurrent;

public class SharedObjectWithVolatileVariable {
    private volatile int count = 0;

    void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        SharedObjectWithVolatileVariable sharedObject1 = new SharedObjectWithVolatileVariable();

        Thread writer1 = new Thread(sharedObject1::incrementCount);
        writer1.start();
        Thread.sleep(100);

        Thread readerOne1 = new Thread(() -> {
            int valueReadByThread2 = sharedObject1.getCount();
            System.out.println(valueReadByThread2);
        });
        readerOne1.start();

        Thread readerTwo1 = new Thread(() -> {
            int valueReadByThread3 = sharedObject1.getCount();
            System.out.println(valueReadByThread3);
        });
        readerTwo1.start();

        SharedObjectWithVolatileVariable sharedObject2 = new SharedObjectWithVolatileVariable();
        Thread writerOne2 = new Thread(sharedObject2::incrementCount);
        writerOne2.start();
        Thread.sleep(100);

        Thread writerTwo2 = new Thread(sharedObject2::incrementCount);
        writerTwo2.start();
        Thread.sleep(100);

        Thread readerOne2 = new Thread(() -> {
            int valueReadByThread2 = sharedObject2.getCount();
            System.out.println(valueReadByThread2);
        });
        readerOne2.start();

        Thread readerTwo2 = new Thread(() -> {
            int valueReadByThread3 = sharedObject2.getCount();
            System.out.println(valueReadByThread3);
        });
        readerTwo2.start();
    }
}
