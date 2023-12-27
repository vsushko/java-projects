package com.vsushko.vtp.concurrencyproblems;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vsushko
 */
public class VirtualThreadsReentrantLock {

    private static final Logger log = LoggerFactory.getLogger(VirtualThreadsReentrantLock.class);

    private static final List<Integer> list = new ArrayList<>();

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        demo(Thread.ofVirtual());

        CommonUtil.sleep(Duration.ofSeconds(2));

        log.info("list size: {}", list.size());
    }

    public static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void inMemoryTask() {
        try {
            lock.lock();
            list.add(1);
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            lock.unlock();
        }
    }
}
