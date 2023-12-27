package com.vsushko.vtp.concurrencyproblems;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
public class SynchronizationWithIO {

    private static final Logger log = LoggerFactory.getLogger(SynchronizationWithIO.class);

    private static final List<Integer> list = new ArrayList<>();

    // Use this to check if virtual threads are getting pinned in your application
    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> log.info("*** TEST Message ***");

//        demo(Thread.ofPlatform());
//        Thread.ofPlatform().start(runnable);

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
        list.add(1);
        CommonUtil.sleep(Duration.ofSeconds(10));
    }
}
