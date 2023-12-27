package com.vsushko.vtp.concurrencyproblems;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vsushko
 */
public class VirtualThreadsIOReentrantLock {

    private static final Logger log = LoggerFactory.getLogger(VirtualThreadsIOReentrantLock.class);

    private static final Lock lock = new ReentrantLock();

    static {
        System.setProperty("jdk.tracePinnedThreads", "short");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> log.info("*** Test Message ***");

        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);

        CommonUtil.sleep(Duration.ofSeconds(15));
    }

    public static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                ioTask();
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void ioTask() {
        try {
            lock.lock();
            CommonUtil.sleep(Duration.ofSeconds(10));
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            lock.unlock();
        }
    }
}
